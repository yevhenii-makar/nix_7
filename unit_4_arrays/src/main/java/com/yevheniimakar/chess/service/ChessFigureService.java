package com.yevheniimakar.chess.service;

import com.yevheniimakar.chess.entity.ChessFigure;

public interface ChessFigureService {

    boolean isCanChessMove(ChessFigure chessFigure, int coordinateX, int coordinateY);
    ChessFigure [][] arrangeFiguresOnBoard();


}
