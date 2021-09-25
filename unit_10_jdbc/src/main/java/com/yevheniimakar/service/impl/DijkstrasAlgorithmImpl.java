package com.yevheniimakar.service.impl;


import com.yevheniimakar.entity.Problem;
import com.yevheniimakar.entity.Solution;
import com.yevheniimakar.node.NodeInGraph;
import com.yevheniimakar.service.DijkstrasAlgorithm;

import java.util.Map;


public class DijkstrasAlgorithmImpl implements DijkstrasAlgorithm {

    private Map<Integer, NodeInGraph> graphMap;

    public Solution getSolution(Map<Integer, NodeInGraph> graphMap, Problem problem) {
        this.graphMap = graphMap;
        int startKey = problem.getFromId();
        int finisKey = problem.getToId();

        graphMap.get(startKey).setPathLength(0);
        algorithmStep(startKey);

        return new Solution(problem.getId(), graphMap.get(finisKey).getPathLength());
    }

    private void algorithmStep(int startIndex) {
        Map<Integer, Integer> indexAndCoast = graphMap.get(startIndex).getIndexNodeAndCost();
        int pathLength = graphMap.get(startIndex).getPathLength();

        for (int key : indexAndCoast.keySet()) {
            int lengthToNextNode = indexAndCoast.get(key);
            int pathLengthInNextNode = graphMap.get(key).getPathLength();
            if ((pathLengthInNextNode >= pathLength + lengthToNextNode || pathLengthInNextNode == -1) && pathLengthInNextNode != 0) {

                graphMap.get(key).setPathLength(pathLength + lengthToNextNode);
                algorithmStep(key);
            }
        }
    }

    private int nodesKey(String nodesName) {
        for (int key : graphMap.keySet()) {
            if (graphMap.get(key).getName().equals(nodesName)) {
                return key;
            }
        }
        return 0;
    }

}
