package com.yevheniimakar.price.service;


import com.yevheniimakar.price.node.NodeInGraph;

import java.util.Map;


public interface DijkstrasAlgorithm {

    int getMinimumPathLength(Map<Integer, NodeInGraph> graphMap, String start, String finish);

}
