package com.yevheniimakar.chess.service.impl;

import com.yevheniimakar.chess.entity.Coordinate;
import com.yevheniimakar.chess.service.CoordinateService;

public class CoordinateServiceImpl implements CoordinateService {
    @Override
    public Coordinate getCoordinateFromString(String chessBoarCoordinate) {

        String coordinateXString = chessBoarCoordinate.replaceAll("\\D", "");
        String coordinateYString = chessBoarCoordinate.replaceAll("[^A-Za-z]","");

        int coordinateX = 8 - Integer.parseInt(coordinateXString);
        int coordinateY = CharCoordinate.valueOf(coordinateYString.toUpperCase()).ordinal();


        return new Coordinate(coordinateX, coordinateY);


    }

     private enum CharCoordinate{
        A,B,C,D,E,F,G,H;
    }

}
