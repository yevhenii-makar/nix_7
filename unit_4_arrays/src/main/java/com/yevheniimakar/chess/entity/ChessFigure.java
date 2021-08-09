package com.yevheniimakar.chess.entity;

public class ChessFigure {

    private String color;
    private String name;
    private boolean isWasTheFirstMove;
    private String symbol;

    public ChessFigure() { }

    public ChessFigure(String color, String name, String symbol) {
        this.color = color;
        this.name = name;
        this.symbol = symbol;
    }
    public ChessFigure(ChessFigure chessFigure) {
        this.color = chessFigure.getColor();
        this.name = chessFigure.getName();
        this.symbol = chessFigure.getSymbol();
        this.isWasTheFirstMove = chessFigure.isWasTheFirstMove();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isWasTheFirstMove() {
        return isWasTheFirstMove;
    }

    public void setWasTheFirstMove(boolean wasTheFirstMove) {
        isWasTheFirstMove = wasTheFirstMove;
    }
}
