package com.yevheniimakar.node.service;

import com.yevheniimakar.entity.Location;
import com.yevheniimakar.node.node.NodeInGraph;

import java.util.List;
import java.util.Map;


public interface  NodeInGraphService {


    Map<Integer, NodeInGraph> createNodInGraphFromLocation();

}
