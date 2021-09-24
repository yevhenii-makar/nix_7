package com.yevheniimakar.dao.impl;

import com.yevheniimakar.dao.SolutionDAO;
import com.yevheniimakar.entity.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SolutionDAOImpl implements SolutionDAO {

    public static final String SQL_INSERT_SOLUTION = "INSERT INTO solutions (problem_id, coast) VALUES (?, ?)";
    Connection connection;

    public SolutionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(Solution solution) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SOLUTION)) {
            connection.setAutoCommit(false);
            statement.setInt(1, solution.getProblemId());
            statement.setInt(2, solution.getCoast());
            statement.addBatch();
            statement.executeBatch();

            connection.commit();
        } catch (SQLException throwables) {
            System.err.println(throwables);
        }
    }

}
