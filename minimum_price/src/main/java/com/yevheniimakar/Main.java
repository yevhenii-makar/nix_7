package com.yevheniimakar;

import com.yevheniimakar.dao.LocationDAO;
import com.yevheniimakar.dao.ProblemDAO;
import com.yevheniimakar.dao.SolutionDAO;
import com.yevheniimakar.entity.Location;
import com.yevheniimakar.entity.Problem;
import com.yevheniimakar.entity.Solution;
import com.yevheniimakar.node.node.NodeInGraph;
import com.yevheniimakar.node.service.NodeInGraphService;
import com.yevheniimakar.node.service.impl.NodeInGraphServiceImpl;
import com.yevheniimakar.task.TaskInputData;

import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        LocationDAO locationDAO = new LocationDAO();
        ProblemDAO problemDAO = new ProblemDAO();
        SolutionDAO solutionDAO = new SolutionDAO();
        NodeInGraphService nodeInGraphService = new NodeInGraphServiceImpl();
        Map<Integer, NodeInGraph> nodeInGraphMap = nodeInGraphService.createNodInGraphFromLocation();
        List<Problem> problemList = problemDAO.getAllWithoutSolutions();
        TaskInputData taskInputData = new TaskInputData(nodeInGraphMap,problemList);




    }
}
