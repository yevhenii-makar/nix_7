package com.yevheniimakar.chess.service.impl;

import com.yevheniimakar.chess.entity.ChessBoardCell;
import com.yevheniimakar.chess.entity.ChessFigure;
import com.yevheniimakar.chess.service.ChessBoardCellService;

public class ChessBoardCellServiceImpl implements ChessBoardCellService {


    @Override
    public ChessBoardCell[][] arrangeFiguresOnBoard() {

        ChessBoardCell[][] chessBoardCells = new ChessBoardCell[8][8];


        chessBoardCells[0][0].setChessFigure(new ChessFigure("black","rock"));
        chessBoardCells[0][7].setChessFigure(new ChessFigure("black","rock"));

        chessBoardCells[7][0].setChessFigure(new ChessFigure("white","rock"));
        chessBoardCells[7][7].setChessFigure(new ChessFigure("white","rock"));

        chessBoardCells[0][1].setChessFigure(new ChessFigure("black","knight"));
        chessBoardCells[0][6].setChessFigure(new ChessFigure("black","knight"));

        chessBoardCells[7][1].setChessFigure(new ChessFigure("white","knight"));
        chessBoardCells[7][6].setChessFigure(new ChessFigure("white","knight"));

        chessBoardCells[0][2].setChessFigure(new ChessFigure("black","bishop"));
        chessBoardCells[0][5].setChessFigure(new ChessFigure("black","bishop"));

        chessBoardCells[7][2].setChessFigure(new ChessFigure("white","bishop"));
        chessBoardCells[7][5].setChessFigure(new ChessFigure("white","bishop"));

        chessBoardCells[0][3].setChessFigure(new ChessFigure("black","queen"));
        chessBoardCells[0][4].setChessFigure(new ChessFigure("black","king"));

        chessBoardCells[7][3].setChessFigure(new ChessFigure("white","queen"));
        chessBoardCells[7][4].setChessFigure(new ChessFigure("white","king"));

        for (int i = 0 ; i<chessBoardCells[0].length; i++) {
            chessBoardCells[1][i].setChessFigure(new ChessFigure("black", "pawn"));
            chessBoardCells[1][i].setChessFigure(new ChessFigure("white", "pawn"));

        }




        return chessBoardCells;
    }
}
