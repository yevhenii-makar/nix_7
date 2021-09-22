package com.yevheniimakar.node.service.impl;

import com.yevheniimakar.dao.LocationDAO;
import com.yevheniimakar.entity.Location;
import com.yevheniimakar.entity.Route;
import com.yevheniimakar.node.node.NodeInGraph;
import com.yevheniimakar.node.service.NodeInGraphService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NodeInGraphServiceImpl implements NodeInGraphService {

    LocationDAO locationDAO = new LocationDAO();

    @Override
    public Map<Integer, NodeInGraph> createNodInGraphFromLocation() {
        List<Location> locations = locationDAO.findAllWithRoutes();
        Map<Integer, NodeInGraph>  nodeInGraphMap = new HashMap<>();

        for (Location location: locations) {
            NodeInGraph nodeInGraph = new NodeInGraph(location.getName(),location.getId());
            Map<Integer,Integer> indexNodeAndCost = new HashMap<>();
            for (Route route: location.getRoutes()) {
                indexNodeAndCost.put(route.getToId(),route.getCoast());
            }
            nodeInGraph.setIndexNodeAndCost(indexNodeAndCost);
            nodeInGraphMap.put(location.getId(),nodeInGraph);
        }
        return nodeInGraphMap;

    }

}
