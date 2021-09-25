package com.yevheniimakar;


import com.yevheniimakar.config.ConnectorDB;
import com.yevheniimakar.dao.LocationDAO;
import com.yevheniimakar.dao.ProblemDAO;
import com.yevheniimakar.dao.RoutesDAO;
import com.yevheniimakar.dao.SolutionDAO;
import com.yevheniimakar.dao.impl.LocationDAOImpl;
import com.yevheniimakar.dao.impl.ProblemDAOImpl;
import com.yevheniimakar.dao.impl.RoutesDAOImpl;
import com.yevheniimakar.dao.impl.SolutionDAOImpl;
import com.yevheniimakar.entity.Problem;
import com.yevheniimakar.entity.Solution;
import com.yevheniimakar.node.NodeInGraph;
import com.yevheniimakar.service.NodeInGraphService;
import com.yevheniimakar.service.impl.DijkstrasAlgorithmImpl;
import com.yevheniimakar.service.impl.NodeInGraphServiceImpl;
import com.yevheniimakar.task.TaskInputData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        LocationDAO locationDAO;
        ProblemDAO problemDAO;
        SolutionDAO solutionDAO;
        RoutesDAO routesDAO;
        NodeInGraphService nodeInGraphService;
        Map<Integer, NodeInGraph> nodeInGraphMap;
        List<Problem> problemList;
        TaskInputData taskInputData;

        try (Connection connection = ConnectorDB.getConnection()) {
            locationDAO = new LocationDAOImpl(connection);
            routesDAO = new RoutesDAOImpl(connection);
            problemDAO = new ProblemDAOImpl(connection);
            solutionDAO = new SolutionDAOImpl(connection);
            nodeInGraphService = new NodeInGraphServiceImpl();
            problemList = problemDAO.getAllWithoutSolutions();
            nodeInGraphMap = nodeInGraphService.createNodInGraph(locationDAO.findAll(), routesDAO.getAll());
            taskInputData = new TaskInputData(nodeInGraphMap, problemList);
            List<Solution> solutionList = new ArrayList<>();
            for (int i = 0; i < taskInputData.getProblems().size(); i++) {
                Solution solution = new DijkstrasAlgorithmImpl().getSolution(taskInputData.getNodeInGraphMap(), taskInputData.getProblems().get(i));
                solutionList.add(solution);
                taskInputData.resetPathLength();
            }
            solutionDAO.saveAll(solutionList);



        } catch (SQLException throwables) {
            System.err.println(throwables);
        }
    }

}
