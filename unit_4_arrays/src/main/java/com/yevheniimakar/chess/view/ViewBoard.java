package com.yevheniimakar.chess.view;

import com.yevheniimakar.chess.entity.ChessBoardCell;

public class ViewBoard {

    private ChessBoardCell[][] chessBoard;

    public void printChessBoard(ChessBoardCell[][] chessBoard){
        this.chessBoard=chessBoard;
        for(int x=0; x<chessBoard.length; x++){
            String index = ""+(8-x);
            System.out.printf("%s%s%s%s%s%s%s%s%s%n",index+" | ",getFigureOrEmpty(x, 0)+" | "
                    ,getFigureOrEmpty(x, 1)+" | ",getFigureOrEmpty(x, 2)+" | ",getFigureOrEmpty(x, 3)+" | "
                    ,getFigureOrEmpty(x, 4)+" | ",getFigureOrEmpty(x, 5)+" | ",getFigureOrEmpty(x, 6)+" | "
                    ,getFigureOrEmpty(x, 7)+" | ");
            System.out.println("___________________________________");
        }
        System.out.printf("%s%s%s%s%s%s%s%s%s%n","  ","  A  "," B  "," C  "," D  "," E  "," F  "," G  "," H  ");
    }

    private String getFigureOrEmpty(int row, int column){
        return chessBoard[row][column].getChessFigure()!=null?chessBoard[row][column].getChessFigure().getSymbol():" ";
    }
}
