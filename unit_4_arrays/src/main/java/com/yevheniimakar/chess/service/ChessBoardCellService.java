package com.yevheniimakar.chess.service;

import com.yevheniimakar.chess.entity.ChessBoardCell;
import com.yevheniimakar.chess.entity.ChessFigure;

public interface ChessBoardCellService {

    ChessBoardCell[][] arrangeFiguresOnBoard();
}
