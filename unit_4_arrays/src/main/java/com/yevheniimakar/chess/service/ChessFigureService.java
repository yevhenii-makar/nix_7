package com.yevheniimakar.chess.service;

import com.yevheniimakar.chess.entity.ChessBoardCell;
import com.yevheniimakar.chess.entity.ChessFigure;
import com.yevheniimakar.chess.entity.Coordinate;

public interface ChessFigureService {

    void moveFigure(Coordinate startCoordinate, Coordinate targetCoordinate, ChessBoardCell[][] chessBoard);

    boolean isCanChessMove(Coordinate starCoordinate, Coordinate targetCoordinate, ChessBoardCell[][] chessBoard);
    boolean isWithoutCheckToKing(String color,ChessBoardCell[][] chessBoard);

}
