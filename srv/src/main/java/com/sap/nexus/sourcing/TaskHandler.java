package com.sap.nexus.sourcing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cds.gen.sourcing.Task.LIMITED_DESCENDANT_COUNT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.sap.cds.Result;
import com.sap.cds.Row;
import com.sap.cds.ql.CQL;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnAnalyzer;
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
import com.sap.cds.services.handler.annotations.After;
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

// elements
private static final String HIERARCHY_TREE_SIZE = "hierarchy_tree_size";
// private static final String HIERARCHY_DISTANCE = "hierarchy_distance";
private static final String HIERARCHY_LEVEL = "hierarchy_level";
// private static final String HIERARCHY_RANK = "hierarchy_rank";

// Drill state
private static final String LEAF = "leaf";
private static final String COLLAPSED = "collapsed";
private static final String EXPANDED = "expanded";

private boolean calcLimitedDescendantsCount;

@Before(event = CqnService.EVENT_READ)
public void beforeRead(CdsReadEventContext event) {
    CqnSelect cqn = event.getCqn();
    if (!CqnAnalyzer.isCountQuery(cqn)) {
        calcLimitedDescendantsCount = cqn.items().stream().flatMap(i -> i.ofValue())
                .anyMatch(v -> LIMITED_DESCENDANT_COUNT.equals(v.displayName()));
        cqn = Select.copy(cqn).columns(List.of()).orderBy(List.of()).inlineCount();
        event.setCqn(cqn);
    }
}

@After(event = CqnService.EVENT_READ)
public void afterRead(CdsReadEventContext event) {
    Result result = event.getResult();
    CqnSelect cqn = event.getCqn();

    addDrillStateAndDistanceFromRoot(result.listOf(Task.class), cqn);
    event.setResult(result);
}

/*
 * Calculate meta for UI: DistanceFromRoot, DrillState, LimitedDescendantCount
 * (if requested by UI via $select)
 */
private void addDrillStateAndDistanceFromRoot(List<Task> rows, CqnSelect select) {
    if (rows.isEmpty() || !rows.get(0).containsKey(HIERARCHY_LEVEL)) {
        // not a hierarchy request
        return;
    }

    Map<String, Task> soByNodeId = new HashMap<>();
    Set<String> parents = rows.stream().peek(so -> soByNodeId.put(so.getNodeId(), so)).map(so -> so.getParentId())
            .filter(p -> p != null)
            .collect(Collectors.toSet());

    for (Task so : rows) {
        String nodeId = so.getNodeId();
        // distance from root
        so.setDistanceFromRoot(((Number) so.remove(HIERARCHY_LEVEL)).longValue() - 1);

        long treeSize = (long) so.remove(HIERARCHY_TREE_SIZE);
        if (treeSize > 1) {
            if (parents.contains(nodeId)) {
                so.setDrillState(EXPANDED);
            } else {
                so.setDrillState(COLLAPSED);
            }
        } else {
            so.setDrillState(LEAF);
        }

        if (calcLimitedDescendantsCount) {
            incrementLimitedDescendant(soByNodeId, nodeId);
        }
    }
}

private void incrementLimitedDescendant(Map<String, Task> soByNodeId, String nodeId) {
    Task so = soByNodeId.get(nodeId);
    if (so != null) {
        long count = (Long) so.getOrDefault(LIMITED_DESCENDANT_COUNT, -1l);
        so.setLimitedDescendantCount(++count);
        incrementLimitedDescendant(soByNodeId, so.getParentId());
    }
}
}
