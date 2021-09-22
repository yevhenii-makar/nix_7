package com.yevheniimakar.node.service;


import com.yevheniimakar.entity.Problem;
import com.yevheniimakar.entity.Solution;
import com.yevheniimakar.node.node.NodeInGraph;

import java.util.Map;


public interface DijkstrasAlgorithm {

    Solution getSolution(Map<Integer, NodeInGraph> graphMap, Problem problem);

}
