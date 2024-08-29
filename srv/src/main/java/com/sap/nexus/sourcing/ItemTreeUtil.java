package com.sap.nexus.sourcing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import com.sap.cds.ql.cqn.CqnSelect;

import cds.gen.sourcing.Item;
import cds.gen.sourcing.Task;
import java.util.stream.Collectors;

public class ItemTreeUtil {


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
public void addDrillStateAndDistanceFromRoot(List<Item> items, CqnSelect select) {
    if (items.isEmpty() || !items.get(0).containsKey(HIERARCHY_LEVEL)) {
        return;
    }

    Map<String, Item> itemByNodeId = new HashMap<String, Item>();
    Set<String> parents = items.stream().peek(item -> itemByNodeId.put(item.getNodeId(), item)).map(item -> item.getParentId())
            .filter(p -> p != null)
            .collect(Collectors.toSet());

    for (Item item : items) {
        String nodeId = item.getNodeId();
        // distance from root
        item.setDistanceFromRoot(((Number) item.remove(HIERARCHY_LEVEL)).longValue() - 1);

        long treeSize = (long) item.remove(HIERARCHY_TREE_SIZE);
        if (treeSize > 1) {
            if (parents.contains(nodeId)) {
                item.setDrillState(EXPANDED);
            } else {
                item.setDrillState(COLLAPSED);
            }
        } else {
            item.setDrillState(LEAF);
        }

        if (calcLimitedDescendantsCount) {
            incrementLimitedDescendant(itemByNodeId, nodeId);
        }
    }
}

private void incrementLimitedDescendant(Map<String, Item> itemByNodeId, String nodeId) {
    Item so = itemByNodeId.get(nodeId);
    System.out.println("Prathee >>> Node ID" + nodeId);
    if (so != null) {
        Object ret = so.getOrDefault(cds.gen.sourcing.Task.LIMITED_DESCENDANT_COUNT, -1l);
        if(ret == null){
            System.out.println("Prathee >>> Node ID is null");
            return;
        }
        
        long count = (Long) ret;
        System.out.println("Prathee >>> count-- " + count + "Node Id---" + nodeId );
        so.setLimitedDescendantCount(++count);
        incrementLimitedDescendant(itemByNodeId, so.getParentId());
    }
}
    
}
