package com.yevheniimakar.chess.service;

import com.yevheniimakar.chess.entity.Coordinate;

public interface CoordinateService {

    Coordinate getCoordinateFromString(String chessBoarCoordinate);
}
