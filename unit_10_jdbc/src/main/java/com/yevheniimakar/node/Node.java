package com.yevheniimakar.node;

import java.util.HashMap;
import java.util.Map;


public class Node {

    private String name;
    private int index;
    private Map<Integer, Integer> indexNodeAndCost = new HashMap<Integer, Integer>();

    public Node(String name, Map<Integer, Integer> indexNodeAndCost, int index) {
        this.name = name;
        this.indexNodeAndCost = indexNodeAndCost;
        this.index = index;
    }

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfNeighbors() {
        return indexNodeAndCost.size();
    }

    public Map<Integer, Integer> getIndexNodeAndCost() {
        return indexNodeAndCost;
    }

    public void setIndexNodeAndCost(Map<Integer, Integer> indexNodeAndCost) {
        this.indexNodeAndCost = indexNodeAndCost;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void putIndexNodeAndCost(int index, int coast) {
        this.indexNodeAndCost.put(index, coast);
    }

    @Override
    public String toString() {
        return "Node{" + "name='" + name + '\'' + ", index=" + index + ", numberOfNeighbors=" + indexNodeAndCost.size() + ", indexNodeAndCost=" + indexNodeAndCost + '}';
    }

}
