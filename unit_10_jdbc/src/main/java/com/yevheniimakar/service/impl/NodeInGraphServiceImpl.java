package com.yevheniimakar.service.impl;


import com.yevheniimakar.entity.Location;
import com.yevheniimakar.entity.Route;
import com.yevheniimakar.node.NodeInGraph;
import com.yevheniimakar.service.NodeInGraphService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NodeInGraphServiceImpl implements NodeInGraphService {


    @Override
    public Map<Integer, NodeInGraph> createNodInGraph(List<Location> locations, List<Route> routes) {

        Map<Integer, NodeInGraph> nodeInGraphMap = new HashMap<>();

        for (Location location : locations) {
            NodeInGraph nodeInGraph = new NodeInGraph(location.getName(), location.getId());
            nodeInGraph.setIndexNodeAndCost(new HashMap<>());
            nodeInGraphMap.put(location.getId(), nodeInGraph);
        }

        for (Route route : routes) {
            nodeInGraphMap.get(route.getFromId()).getIndexNodeAndCost().put(route.getToId(), route.getCoast());
        }
        return nodeInGraphMap;
    }

}
