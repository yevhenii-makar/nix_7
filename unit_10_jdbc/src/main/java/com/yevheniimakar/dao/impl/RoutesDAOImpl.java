package com.yevheniimakar.dao.impl;

import com.yevheniimakar.config.ConnectorDB;
import com.yevheniimakar.dao.RoutesDAO;
import com.yevheniimakar.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoutesDAOImpl implements RoutesDAO {

    public static final String SQL_SELECT_BY_FROM_ID = "SELECT * FROM routes WHERE from_id = ?";
    public static final String SQL_SELECT_ALL_ROUTES = "SELECT * FROM routes";
    Connection connection;

    public RoutesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Route> getByLocationId(int locationId) {

        List<Route> routes = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_FROM_ID)) {
            statement.setInt(1, locationId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int fromId = rs.getInt("from_id");
                int toId = rs.getInt("to_id");
                int coast = rs.getInt("coast");
                routes.add(new Route(id, fromId, toId, coast));
            }
        } catch (SQLException throwables) {
            System.err.println(throwables);
        }
        return routes;
    }

    public List<Route> getAll() {

        List<Route> routes = new ArrayList<>();

        try (Connection connection = ConnectorDB.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_ROUTES);
            while (rs.next()) {
                int id = rs.getInt("id");
                int fromId = rs.getInt("from_id");
                int toId = rs.getInt("to_id");
                int coast = rs.getInt("coast");
                routes.add(new Route(id, fromId, toId, coast));
            }
        } catch (SQLException throwables) {
            System.err.println(throwables);
        }
        return routes;
    }

}
