package com.sap.nexus.sourcing;

import java.util.ArrayList;
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

import com.sap.cds.Result;
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

// @Component
// @RequestScope
// @ServiceName(Sourcing_.CDS_NAME)
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

    private void setResult(CdsReadEventContext event, List<Task> result) {
        addDrillState(result);

        event.setResult(result);
    }

    private List<Task> handleAncestors(CdsReadEventContext event, CqnAncestorsTransformation ancestors,
            CqnTopLevelsTransformation topLevels) {
        CqnTransformation trafo = ancestors.transformations().get(0);
        Select<Task_> inner = Select.from(TASK).where(so -> so.node_id().eq(CQL.get("$outer.node_id")));
        if (trafo instanceof CqnFilterTransformation filter) {
            inner.where(filter.filter());
        } else if (trafo instanceof CqnSearchTransformation search) {
            inner.search(search.search());
        }
        Select<Task_> outer = Select.from(TASK).columns(so -> so.node_id().as("i0"), so -> so.parent().node_id().as("i1"),
                so -> so.parent().parent().node_id().as("i2"),
                so -> so.parent().parent().parent().node_id().as("i3"), so -> so.parent().parent().parent().parent().node_id().as("i4")).where(so -> so.exists($outer -> inner));

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
        all.forEach(so -> lookup.put(so.getNodeId(), so));
        all.forEach(so -> so.setParent(lookup.get(so.getParentId())));
        all.forEach(so -> so.setDistanceFromRoot(dfr(so)));

        return all.stream().sorted(new Sorter()).toList();
    }

    private static long dfr(Task so) {
        long dfr = 0;
        while (so.getParent() != null) {
            dfr++;
            so = so.getParent();
        }

        return dfr;
    }

    private List<Task> topLevelsLimit(long limit, CqnPredicate filter) {
        Map<String, Task> lookup = new HashMap<>();

        CqnSelect getRoot = Select.from(TASK).where(so -> so.parent_id().isNull().and(filter));
         Result res = db.run(getRoot);
         if(res.rowCount() == 0 ){
            return new ArrayList<>();
         }
         Task root = res.single(Task.class);
        root.setDistanceFromRoot(0l);
        lookup.put(root.getNodeId(), root);
        List<String> parents = List.of(root.getNodeId());
        for (long i = 1; i < limit; i++) {
            List<String> ps = parents;
            CqnSelect getChildren = Select.from(TASK).where(so -> so.parent_id().in(ps).and(filter));
            List<Task> children = db.run(getChildren).listOf(Task.class);
            if (children.isEmpty()) {
                break;
            }
            long dfr = i;
            parents = children.stream().peek(so -> {
                so.setParent(lookup.get(so.getParentId()));
                so.setDistanceFromRoot(dfr);
                lookup.put(so.getNodeId(), so);
            }).map(Task::getNodeId).toList();
        }

        return lookup.values().stream().sorted(new Sorter()).toList();
    }

    private void addDrillState(List<Task> sos) {
        List<String> ids = sos.stream().map(so -> so.getNodeId()).toList();
        Set<String> parents = sos.stream().map(so -> so.getParentId()).filter(p -> p != null).collect(Collectors.toSet());
        CqnSelect q = Select.from(TASK, so -> so.parent()).columns(so -> so.node_id())
                .where(so -> so.node_id().in(ids));
        Set<String> nonLeafs = db
                .run(q)
                .stream().map(r -> (String) r.get(Task.NODE_ID)).collect(Collectors.toSet());

        for (Task so : sos) {
            String id = so.getNodeId();
            if (nonLeafs.contains(id)) {
                if (parents.contains(id)) {
                    so.setDrillState("expanded");
                } else {
                    so.setDrillState("collapsed");
                }
            } else {
                so.setDrillState("leaf");
            }
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
        children.forEach(so -> so.setDrillState("collapsed"));

        return children;
    }

    private class Sorter implements Comparator<Task> {

        @Override
        public int compare(Task o1, Task o2) {
            return toPath(o1).compareTo(toPath(o2));
        }

        String toPath(Task so) {
            String path;

            path = so.getNodeId();
            while (so.getParent() != null) {
                so = so.getParent();
                path = so.getNodeId() + "." + path;
            }

            return path;

        }
    }
    
}
