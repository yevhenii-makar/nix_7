package com.yevheniimakar.dao;

import com.yevheniimakar.config.ConnectorDB;
import com.yevheniimakar.entity.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class LocationDAO {
    public static final String SQL_SELECT_ALL_LOCATION ="SELECT * FROM locations";
    public static final String SQL_SELECT_BY_ID ="SELECT * FROM locations WHERE id = ?";

    RoutesDAO routesDao = new RoutesDAO();

    public List<Location> findAll(){

        List<Location>  locations = new ArrayList<>();
        try(Connection connection = ConnectorDB.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_LOCATION);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                locations.add(new Location(id,name));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return locations;
    }
    public List<Location> findAllWithRoutes(){
        List<Location> locations = findAll();

        for (Location location: locations) {
            location.setRoutes(routesDao.getByLocationId(location.getId()));

        }



        return locations;

    }



}
