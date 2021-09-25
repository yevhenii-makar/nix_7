package com.yevheniimakar.dao.impl;

import com.yevheniimakar.dao.LocationDAO;
import com.yevheniimakar.entity.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LocationDAOImpl implements LocationDAO {

    public static final String SQL_SELECT_ALL_LOCATION = "SELECT * FROM locations";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM locations WHERE id = ?";
    private final Connection connection;


    public LocationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Location> findAll() {

        List<Location> locations = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_LOCATION);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                locations.add(new Location(id, name));
            }
        } catch (SQLException throwables) {
            System.err.println(throwables);
        }
        return locations;
    }

}
