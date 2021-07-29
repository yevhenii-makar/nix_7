package com.yevheniimakar.chess.entity;

public class ChessBoardCell {
    private ChessFigure chessFigure;

    public ChessFigure getChessFigure() {
        return chessFigure;
    }

    public void setChessFigure(ChessFigure chessFigure) {
        this.chessFigure = chessFigure;
    }

    public boolean isHaveChessFigureInCell(){
        return chessFigure!=null;
    }

    public void removeFigure(){
        chessFigure = null;
    }
    public ChessBoardCell() {

    }

    public ChessBoardCell(ChessFigure chessFigure) {
        this.chessFigure = chessFigure;
    }
}
