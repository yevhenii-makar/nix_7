package com.yevheniimakar.dao;

import com.yevheniimakar.config.ConnectorDB;
import com.yevheniimakar.entity.Location;
import com.yevheniimakar.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoutesDAO {

    public static final String SQL_SELECT_BY_FROM_ID ="SELECT * FROM routes WHERE id = ?";


    public List<Route> getByLocationId(int locationId){

        List<Route>  routes = new ArrayList<>();
        try(Connection connection = ConnectorDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_FROM_ID)) {
            statement.setInt(1, locationId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int fromId = rs.getInt("from_id");
                int toId = rs.getInt("to_id");
                int coast = rs.getInt("coast");
                String name = rs.getString("name");
                routes.add(new Route(id,fromId,toId,coast));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return routes;
    }

}
