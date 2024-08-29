package com.sap.nexus.sourcing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import com.sap.cds.ql.cqn.CqnSelect;

import cds.gen.sourcing.Task;
import java.util.stream.Collectors;

public class TaskTreeUtil {


    // elements
    private static final String HIERARCHY_TREE_SIZE = "hierarchy_tree_size";
    // private static final String HIERARCHY_DISTANCE = "hierarchy_distance";
    private static final String HIERARCHY_LEVEL = "hierarchy_level";

    // Drill state
    private static final String LEAF = "leaf";
    private static final String COLLAPSED = "collapsed";
    private static final String EXPANDED = "expanded";

    public boolean calcLimitedDescendantsCount;
        /*
 * Calculate meta for UI: DistanceFromRoot, DrillState, LimitedDescendantCount
 * (if requested by UI via $select)
 */
public void addDrillStateAndDistanceFromRoot(List<Task> tasks, CqnSelect select) {
    if (tasks.isEmpty() || !tasks.get(0).containsKey(HIERARCHY_LEVEL)) {
        return;
    }

    Map<String, Task> taskByNodeId = new HashMap<String, Task>();
    Set<String> parents = tasks.stream().peek(task -> taskByNodeId.put(task.getNodeId(), task)).map(task -> task.getParentId())
            .filter(p -> p != null)
            .collect(Collectors.toSet());

    for (Task task : tasks) {
        String nodeId = task.getNodeId();
        // distance from root
        task.setDistanceFromRoot(((Number) task.remove(HIERARCHY_LEVEL)).longValue() - 1);

        long treeSize = (long) task.remove(HIERARCHY_TREE_SIZE);
        if (treeSize > 1) {
            if (parents.contains(nodeId)) {
                task.setDrillState(EXPANDED);
            } else {
                task.setDrillState(COLLAPSED);
            }
        } else {
            task.setDrillState(LEAF);
        }

        if (calcLimitedDescendantsCount) {
            incrementLimitedDescendant(taskByNodeId, nodeId);
        }
    }
}

private void incrementLimitedDescendant(Map<String, Task> taskByNodeId, String nodeId) {
    Task task = taskByNodeId.get(nodeId);
    System.out.println("Prathee >>> Node ID" + nodeId);
    if (task != null) {
        Object ret = task.getOrDefault(cds.gen.sourcing.Task.LIMITED_DESCENDANT_COUNT, -1l);
        if(ret == null){
            System.out.println("Prathee >>> Node ID is null");
            return;
        }
        
        long count = (Long) ret;
        System.out.println("Prathee >>> count-- " + count + "Node Id---" + nodeId );
        task.setLimitedDescendantCount(++count);
        incrementLimitedDescendant(taskByNodeId, task.getParentId());
    }
}
    
}
