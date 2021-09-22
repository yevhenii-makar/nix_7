package com.yevheniimakar.dao;

import com.yevheniimakar.config.ConnectorDB;
import com.yevheniimakar.entity.Problem;
import com.yevheniimakar.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProblemDAO {
    public static final String SQL_SELECT_All_WITHOUT_SOLUTIONS ="SELECT * FROM problems WHERE id NOT IN (select problem_id from  solutions)";


    public List<Problem> getAllWithoutSolutions(){

        List<Problem>  problems = new ArrayList<>();
        try(Connection connection = ConnectorDB.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(SQL_SELECT_All_WITHOUT_SOLUTIONS);
            while (rs.next()){
                int id = rs.getInt("id");
                int fromId = rs.getInt("from_id");
                int toId = rs.getInt("to_id");
                problems.add(new Problem(id,fromId,toId));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return problems;
    }
}
