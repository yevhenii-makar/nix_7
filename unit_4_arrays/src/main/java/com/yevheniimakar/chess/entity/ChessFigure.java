package com.yevheniimakar.chess.entity;

public class ChessFigure {
    private String color;
    private String name;
    boolean isWasTheFirstMove;

    public ChessFigure() {
    }

    public ChessFigure(String color, String name) {
        this.color = color;
        this.name = name;
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

    public boolean isWasTheFirstMove() {
        return isWasTheFirstMove;
    }

    public void setWasTheFirstMove(boolean wasTheFirstMove) {
        isWasTheFirstMove = wasTheFirstMove;
    }
}
