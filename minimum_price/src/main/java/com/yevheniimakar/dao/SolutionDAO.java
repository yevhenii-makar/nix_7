package com.yevheniimakar.dao;

import com.yevheniimakar.config.ConnectorDB;
import com.yevheniimakar.entity.Route;
import com.yevheniimakar.entity.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SolutionDAO {
    public static final String SQL_INSERT_SOLUTION = "INSERT INTO solutions (problem_id, coast) VALUES (?, ?)";


    public void save(Solution solution){
        try(Connection connection = ConnectorDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SOLUTION)) {
            connection.setAutoCommit(false);
            statement.setInt(1, solution.getProblemId());
            statement.setInt(2, solution.getCoast());
            statement.addBatch();
            statement.executeBatch();

            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
