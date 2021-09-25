package com.yevheniimakar.task;

/**
 * reflection api
 * meta programing
 */

import com.yevheniimakar.entity.Problem;
import com.yevheniimakar.node.NodeInGraph;

import java.util.List;
import java.util.Map;


public class TaskInputData {


    List<Problem> problems;
    private Map<Integer, NodeInGraph> nodeInGraphMap;

    public TaskInputData(Map<Integer, NodeInGraph> nodeInGraphMap, List<Problem> problems) {

        this.nodeInGraphMap = nodeInGraphMap;
        this.problems = problems;
    }

    public Map<Integer, NodeInGraph> getNodeInGraphMap() {
        return nodeInGraphMap;
    }

    public void setNodeInGraphMap(Map<Integer, NodeInGraph> nodeInGraphMap) {
        this.nodeInGraphMap = nodeInGraphMap;
    }


    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public void resetPathLength() {
        for (int i : nodeInGraphMap.keySet()) {
            nodeInGraphMap.get(i).setPathLength(-1);
        }
    }

}
