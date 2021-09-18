package com.yevheniimakar.price.node;

import java.util.Map;


public class NodeInGraph extends Node {

    private int pathLength = -1;

    public NodeInGraph(String name, Map<Integer, Integer> indexNodeAndCost, int index) {
        super(name, indexNodeAndCost, index);
    }

    public NodeInGraph(String name, int index) {
        super(name, index);
    }

    public int getPathLength() {
        return pathLength;
    }

    public void setPathLength(int pathLength) {
        this.pathLength = pathLength;
    }

    @Override
    public String toString() {
        return "NodeInGraph{" + "pathLength=" + pathLength + '}' + "Node{" + "name='" + getName() + '\'' + ", index=" + getIndex() + ", numberOfNeighbors=" + getNumberOfNeighbors() + ", indexNodeAndCost=" + getIndexNodeAndCost() + "}\n";
    }

}
