package com.sap.nexus.sourcing;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.sap.cds.Row;
import com.sap.cds.ql.CQL;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnElementRef;
import com.sap.cds.ql.cqn.CqnPredicate;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnValue;
import com.sap.cds.ql.cqn.transformation.CqnAncestorsTransformation;
import com.sap.cds.ql.cqn.transformation.CqnDescendantsTransformation;
import com.sap.cds.ql.cqn.transformation.CqnFilterTransformation;
import com.sap.cds.ql.cqn.transformation.CqnSearchTransformation;
import com.sap.cds.ql.cqn.transformation.CqnTopLevelsTransformation;
import com.sap.cds.ql.cqn.transformation.CqnTransformation;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import com.sap.cds.ql.cqn.Modifier;

import cds.gen.sourcing.Sourcing_;
import cds.gen.sourcing.Task;
import cds.gen.sourcing.Task_;

@Component
@RequestScope
@ServiceName(Sourcing_.CDS_NAME)
public class TaskHandler implements EventHandler  {

    private static final Class<Task_> TASK = Task_.class;

    @Autowired
    PersistenceService db;

    @Before(event = CqnService.EVENT_READ)
    public void readSalesOrganizations(CdsReadEventContext event) {
        // handle
        // $apply=com.sap.vocabularies.Hierarchy.v1.TopLevels(HierarchyNodes=$root/SalesOrganizations,
        // HierarchyQualifier='SalesOrgHierarchy',NodeProperty='ID',Levels=2
        CqnSelect select = event.getCqn();
        List<CqnTransformation> trafos = select.transformations();
        if (trafos.size() == 1 && trafos.get(0) instanceof CqnTopLevelsTransformation topLevels) {
            List<Task> result = topLevels(topLevels, CQL.TRUE);
            setResult(event, result);
        } else if (trafos.size() == 1 && trafos.get(0) instanceof CqnDescendantsTransformation descendants) {
            List<Task> result = handleDescendants(event, descendants);
            setResult(event, result);
        } else if (trafos.size() == 2 && trafos.get(0) instanceof CqnAncestorsTransformation ancestors
                && trafos.get(1) instanceof CqnTopLevelsTransformation topLevels) {
            List<Task> result = handleAncestors(event, ancestors, topLevels);
            setResult(event, result);
        }
    }


    private List<Task> handleDescendants(CdsReadEventContext event,
            CqnDescendantsTransformation descendants) {
        CqnFilterTransformation filter = (CqnFilterTransformation) descendants.transformations().get(0);

        CqnPredicate parentFilter = CQL.copy(filter.filter(), new Modifier() {
            @Override
            public CqnValue ref(CqnElementRef ref) {
                return CQL.get(Task.PARENT_ID);
            }
        });

        CqnSelect getChildren = Select.from(TASK).where(parentFilter);
        List<Task> children = db.run(getChildren).listOf(Task.class);
        children.forEach(task -> task.setDrillState("collapsed"));

        return children;
    }


    private void setResult(CdsReadEventContext event, List<Task> result) {
        addDrillState(result);

        event.setResult(result);
    }

    private void addDrillState(List<Task> tasks) {
        List<String> ids = tasks.stream().map(task -> task.getId()).toList();
        Set<String> parents = tasks.stream().map(task -> task.getParentID()).filter(p -> p != null).collect(Collectors.toSet());
        CqnSelect q = Select.from(TASK, task -> task.Parent()).columns(task -> task.ID())
                .where(task -> task.ID().in(ids));
        Set<String> nonLeafs = db
                .run(q)
                .stream().map(r -> (String) r.get(Task.ID)).collect(Collectors.toSet());

        for (Task task : tasks) {
            String id = task.getId();
            if (nonLeafs.contains(id)) {
                if (parents.contains(id)) {
                    task.setDrillState("expanded");
                } else {
                    task.setDrillState("collapsed");
                }
            } else {
                task.setDrillState("leaf");
            }
        }
    }

    private List<Task> topLevels(CqnTopLevelsTransformation topLevels, CqnPredicate filter) {
        long limit = topLevels.levels();
        if (limit < 0) {
            return topLevelsAll(topLevels, filter);
        } else {
            return topLevelsLimit(limit, filter);
        }
    }


    private List<Task> topLevelsAll(CqnTopLevelsTransformation topLevels, CqnPredicate filter) {
        Map<String, Task> lookup = new HashMap<>();

        CqnSelect getAll = Select.from(TASK).where(filter);
        var all = db.run(getAll).listOf(Task.class);
        all.forEach(task -> lookup.put(task.getId(), task));
        all.forEach(task -> task.setParent(lookup.get(task.getParentID())));
        all.forEach(task -> task.setDistanceFromRoot(dfr(task)));

        return all.stream().sorted(new Sorter()).toList();
    }

    private static long dfr(Task task) {
        long dfr = 0;
        while (task.getParent() != null) {
            dfr++;
            task = task.getParent();
        }

        return dfr;
    }

    private List<Task> topLevelsLimit(long limit, CqnPredicate filter) {
        Map<String, Task> lookup = new HashMap<>();

        CqnSelect getRoot = Select.from(TASK).where(task -> task.ParentID().isNull().and(filter));
        Task root = db.run(getRoot).single(Task.class);
        root.setDistanceFromRoot(0l);
        lookup.put(root.getId(), root);
        List<String> parents = List.of(root.getId());
        for (long i = 1; i < limit; i++) {
            List<String> ps = parents;
            CqnSelect getChildren = Select.from(TASK).where(task -> task.ParentID().in(ps).and(filter));
            List<Task> children = db.run(getChildren).listOf(Task.class);
            if (children.isEmpty()) {
                break;
            }
            long dfr = i;
            parents = children.stream().peek(so -> {
                so.setParent(lookup.get(so.getParentID()));
                so.setDistanceFromRoot(dfr);
                lookup.put(so.getId(), so);
            }).map(Task::getId).toList();
        }

        return lookup.values().stream().sorted(new Sorter()).toList();
    }  

    private List<Task> handleAncestors(CdsReadEventContext event, CqnAncestorsTransformation ancestors,
            CqnTopLevelsTransformation topLevels) {
        CqnTransformation trafo = ancestors.transformations().get(0);
        Select<Task_> inner = Select.from(TASK).where(task -> task.ID().eq(CQL.get("$outer.ID")));
        if (trafo instanceof CqnFilterTransformation filter) {
            inner.where(filter.filter());
        } else if (trafo instanceof CqnSearchTransformation search) {
            inner.search(search.search());
        }
        Select<Task_> outer = Select.from(TASK).columns(task -> task.ID().as("i0"), task -> task.Parent().ID().as("i1"),
                so -> so.Parent().Parent().ID().as("i2"),
                so -> so.Parent().Parent().Parent().ID().as("i3"), task -> task.Parent().Parent().Parent().Parent().ID().as("i4")).where(task -> task.exists($outer -> inner));

                Set<String> ancestorIds = new HashSet<>();
        db.run(outer).stream().forEach(r -> {
            addIfNotNull(ancestorIds, r, "i0");
            addIfNotNull(ancestorIds, r, "i1");
            addIfNotNull(ancestorIds, r, "i2");
            addIfNotNull(ancestorIds, r, "i3");
            addIfNotNull(ancestorIds, r, "i4");
        }); 

        CqnPredicate filter = CQL.get("node_id").in(ancestorIds.stream().toList());
        return topLevels(topLevels, filter);
    }

    private void addIfNotNull(Set<String> ancestorIds, Row r, String key) {
        String id = (String) r.get(key);
        if (id != null) {
            ancestorIds.add(id);
        }
    }
    
    private class Sorter implements Comparator<Task> {

        @Override
        public int compare(Task task1, Task task2) {
            return toPath(task1).compareTo(toPath(task2));
        }

        String toPath(Task task) {
            String path;

            path = task.getId();
            while (task.getParent() != null) {
                task = task.getParent();
                path = task.getId() + "." + path;
            }

            return path;

        }
    }
    
}
