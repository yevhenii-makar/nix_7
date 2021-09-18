package com.yevheniimakar.price.task;


import com.yevheniimakar.price.node.NodeInGraph;

import java.util.List;
import java.util.Map;


public class TaskInputData {

    private int citiesCount;
    private Map<Integer, NodeInGraph> nodeInGraphMap;
    private int waysCount;
    List<String> startCity;
    List<String> finishCity;

    public TaskInputData(int citiesCount, Map<Integer, NodeInGraph> nodeInGraphMap, int waysCount, List<String> startCity, List<String> finishCity) {
        this.citiesCount = citiesCount;
        this.nodeInGraphMap = nodeInGraphMap;
        this.waysCount = waysCount;
        this.startCity = startCity;
        this.finishCity = finishCity;
    }

    public int getCitiesCount() {
        return citiesCount;
    }

    public void setCitiesCount(int citiesCount) {
        this.citiesCount = citiesCount;
    }

    public Map<Integer, NodeInGraph> getNodeInGraphMap() {
        return nodeInGraphMap;
    }

    public void setNodeInGraphMap(Map<Integer, NodeInGraph> nodeInGraphMap) {
        this.nodeInGraphMap = nodeInGraphMap;
    }

    public int getWaysCount() {
        return waysCount;
    }

    public void setWaysCount(int waysCount) {
        this.waysCount = waysCount;
    }

    public List<String> getStartCity() {
        return startCity;
    }

    public void setStartCity(List<String> startCity) {
        this.startCity = startCity;
    }

    public List<String> getFinishCity() {
        return finishCity;
    }

    public void setFinishCity(List<String> finishCity) {
        this.finishCity = finishCity;
    }

}
