package com.yevheniimakar.dao.impl;

import com.yevheniimakar.dao.ProblemDAO;
import com.yevheniimakar.entity.Problem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProblemDAOImpl implements ProblemDAO {

    public static final String SQL_SELECT_All_WITHOUT_SOLUTIONS = "SELECT * FROM problems WHERE id NOT IN (select problem_id from  solutions)";
    Connection connection;

    public ProblemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Problem> getAllWithoutSolutions() {

        List<Problem> problems = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(SQL_SELECT_All_WITHOUT_SOLUTIONS);
            while (rs.next()) {
                int id = rs.getInt("id");
                int fromId = rs.getInt("from_id");
                int toId = rs.getInt("to_id");
                problems.add(new Problem(id, fromId, toId));
            }
        } catch (SQLException throwables) {
            System.err.println(throwables);
        }
        return problems;
    }

}
