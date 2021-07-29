package com.yevheniimakar.chess.service.impl;

import com.yevheniimakar.chess.entity.ChessBoardCell;
import com.yevheniimakar.chess.entity.ChessFigure;
import com.yevheniimakar.chess.service.ChessBoardCellService;

public class ChessBoardCellServiceImpl implements ChessBoardCellService {


    @Override
    public ChessBoardCell[][] arrangeFiguresOnBoard() {

        ChessBoardCell[][] chessBoardCells = new ChessBoardCell[8][8];
        for(int x=0; x<chessBoardCells.length; x++){
            for(int y=0; y<chessBoardCells[x].length; y++){
                chessBoardCells[x][y]= new ChessBoardCell();
            }
        }

        chessBoardCells[0][0].setChessFigure(new ChessFigure("black", "rock", "\u2656"));
        chessBoardCells[0][7].setChessFigure(new ChessFigure("black", "rock", "\u2656"));

        chessBoardCells[7][0].setChessFigure(new ChessFigure("white", "rock","\u265c"));
        chessBoardCells[7][7].setChessFigure(new ChessFigure("white", "rock","\u265c"));

        chessBoardCells[0][1].setChessFigure(new ChessFigure("black", "knight","\u2658"));
        chessBoardCells[0][6].setChessFigure(new ChessFigure("black", "knight","\u2658"));

        chessBoardCells[7][1].setChessFigure(new ChessFigure("white", "knight","\u265e"));
        chessBoardCells[7][6].setChessFigure(new ChessFigure("white", "knight", "\u265e"));

        chessBoardCells[0][2].setChessFigure(new ChessFigure("black", "bishop", "\u2657"));
        chessBoardCells[0][5].setChessFigure(new ChessFigure("black", "bishop", "\u2657"));

        chessBoardCells[7][2].setChessFigure(new ChessFigure("white", "bishop", "\u265d"));
        chessBoardCells[7][5].setChessFigure(new ChessFigure("white", "bishop", "\u265d"));

        chessBoardCells[0][3].setChessFigure(new ChessFigure("black", "queen", "\u2655"));
        chessBoardCells[0][4].setChessFigure(new ChessFigure("black", "king", "\u2654"));

        chessBoardCells[7][3].setChessFigure(new ChessFigure("white", "queen", "\u265b"));
        chessBoardCells[7][4].setChessFigure(new ChessFigure("white", "king", "\u265a"));

        for (int i = 0; i < chessBoardCells[0].length; i++) {
            chessBoardCells[1][i].setChessFigure(new ChessFigure("black", "pawn", "\u2659"));
            chessBoardCells[6][i].setChessFigure(new ChessFigure("white", "pawn", "\u265f"));

        }

        return chessBoardCells;
    }
}
