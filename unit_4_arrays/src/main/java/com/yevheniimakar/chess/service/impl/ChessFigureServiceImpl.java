package com.yevheniimakar.chess.service.impl;

import com.yevheniimakar.chess.entity.ChessBoardCell;
import com.yevheniimakar.chess.entity.ChessFigure;
import com.yevheniimakar.chess.entity.Coordinate;
import com.yevheniimakar.chess.service.ChessFigureService;

public class ChessFigureServiceImpl implements ChessFigureService {
    ChessBoardCell[][] chessBoard;
    String color;

    @Override
    public void moveFigure(Coordinate startCoordinate, Coordinate targetCoordinate, ChessBoardCell[][] chessBoardOriginal) {
        chessBoardOriginal[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoardOriginal[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
        chessBoardOriginal[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].getChessFigure().setWasTheFirstMove(true);
        chessBoardOriginal[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
    }

    @Override
    public boolean isCanChessMove(Coordinate startCoordinate, Coordinate targetCoordinate, ChessBoardCell[][] chessBoardOriginal) {
        this.chessBoard = cloneChessBoard(chessBoardOriginal);
        boolean isCanChessMove = false;
        this.color = chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure().getColor();

        boolean isHaveChessFigureInTargetCell = chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].isHaveChessFigureInCell();
        String chessFigureName = chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure().getName();

        if (startCoordinate.getCoordinateX() == targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() == targetCoordinate.getCoordinateY()) {
            return false;
        }

        if (chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].getChessFigure() != null
                && color.equals(chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].getChessFigure().getColor())) {
            return false;
        }

        int coordinateXDifference = startCoordinate.getCoordinateX() >= targetCoordinate.getCoordinateX() ? startCoordinate.getCoordinateX() - targetCoordinate.getCoordinateX() : (startCoordinate.getCoordinateX() - targetCoordinate.getCoordinateX()) * (-1);
        int coordinateYDifference = startCoordinate.getCoordinateY() >= targetCoordinate.getCoordinateY() ? startCoordinate.getCoordinateY() - targetCoordinate.getCoordinateY() : (startCoordinate.getCoordinateY() - targetCoordinate.getCoordinateY()) * (-1);

        switch (chessFigureName) {
            case "rock": {
                if ((coordinateXDifference == 0 && coordinateYDifference != 0) || (coordinateXDifference != 0 && coordinateYDifference == 0)) {
                    if (isWithoutLineObstacles(startCoordinate, targetCoordinate)){
                        chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
                        chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
                        if (isWithoutCheckToKing(getKingCoordinate())) {
                            isCanChessMove = true;
                        }
                    }
                }
            }
            break;

            case "knight": {
                if ((coordinateXDifference == 2 && coordinateYDifference == 1) || (coordinateXDifference == 1 && coordinateYDifference == 2)) {
                    chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
                    chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
                    if (isWithoutCheckToKing(getKingCoordinate())) {
                        isCanChessMove = true;
                    }
                }
            }
            break;

            case "bishop": {
                if (coordinateXDifference == coordinateYDifference) {
                    if (isWithoutDiagonalObstacles(startCoordinate, targetCoordinate)) {
                        chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
                        chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
                        if (isWithoutCheckToKing(getKingCoordinate())) {
                            isCanChessMove = true;
                        }
                    }
                }
            }
            break;

            case "queen": {
                if ((coordinateXDifference == 0 && coordinateYDifference != 0) || (coordinateXDifference != 0 && coordinateYDifference == 0) || (coordinateXDifference == coordinateYDifference)) {
                    chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
                    chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
                    if (isWithoutCheckToKing(getKingCoordinate())) {
                        isCanChessMove = true;
                    }
                }
            }
            break;

            case "king": {
                if ((coordinateXDifference == 0 || coordinateXDifference == 1) && (coordinateYDifference == 0 || coordinateYDifference == 1)) {
                    chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
                    chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
                    if (isWithoutCheckToKing(getKingCoordinate())) {
                        isCanChessMove = true;
                    }
                }
            }
            break;

            case "pawn": {
                int directionMove = color.equals("black") ? -1 : 1;

                if (isHaveChessFigureInTargetCell) {
                    if (startCoordinate.getCoordinateX() == (targetCoordinate.getCoordinateX() + (1 * directionMove))) {
                        if (startCoordinate.getCoordinateY() == (targetCoordinate.getCoordinateY() + 1)
                                || startCoordinate.getCoordinateY() == (targetCoordinate.getCoordinateY() - 1)) {
                            chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
                            chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
                            if (isWithoutCheckToKing(getKingCoordinate())) {
                                isCanChessMove = true;
                            }
                        }
                    }

                } else if (!isHaveChessFigureInTargetCell) {
                    if (startCoordinate.getCoordinateX() == (targetCoordinate.getCoordinateX() + (1 * directionMove))
                            || (!chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure().isWasTheFirstMove() && startCoordinate.getCoordinateX() == (targetCoordinate.getCoordinateX() + (2 * directionMove)))) {
                        if (startCoordinate.getCoordinateY() == targetCoordinate.getCoordinateY()) {
                            chessBoard[targetCoordinate.getCoordinateX()][targetCoordinate.getCoordinateY()].setChessFigure(new ChessFigure(chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure()));
                            chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].removeFigure();
                            if (isWithoutCheckToKing(getKingCoordinate())) {
                                isCanChessMove = true;
                            }
                        }
                    }
                }
            }
            break;
        }
        return isCanChessMove;
    }

    private boolean isWithoutDiagonalObstacles(Coordinate startCoordinate, Coordinate targetCoordinate) {
        boolean isWithoutDiagonalObstacles = true;
        if (startCoordinate.getCoordinateX() > targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() > targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateX() - x) > targetCoordinate.getCoordinateX(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX() - x][startCoordinate.getCoordinateY() - x].isHaveChessFigureInCell()) {
                    isWithoutDiagonalObstacles = false;
                    break;
                }
            }
        }
        if (startCoordinate.getCoordinateX() > targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() < targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateX() - x) > targetCoordinate.getCoordinateX(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX() - x][startCoordinate.getCoordinateY() + x].isHaveChessFigureInCell()) {
                    isWithoutDiagonalObstacles = false;
                    break;
                }
            }
        }

        if (startCoordinate.getCoordinateX() < targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() < targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateX() + x) < targetCoordinate.getCoordinateX(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX() + x][startCoordinate.getCoordinateY() + x].isHaveChessFigureInCell()) {
                    isWithoutDiagonalObstacles = false;
                    break;
                }
            }
        }

        if (startCoordinate.getCoordinateX() < targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() > targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateX() + x) > targetCoordinate.getCoordinateX(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX() + x][startCoordinate.getCoordinateY() - x].isHaveChessFigureInCell()) {
                    isWithoutDiagonalObstacles = false;
                    break;
                }
            }
        }

        return isWithoutDiagonalObstacles;
    }

    private boolean isWithoutLineObstacles(Coordinate startCoordinate, Coordinate targetCoordinate) {

        boolean isWithoutLineObstacles = true;

        if (startCoordinate.getCoordinateX() == targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() > targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateY() - x) > targetCoordinate.getCoordinateY(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY() - x].isHaveChessFigureInCell()) {
                    isWithoutLineObstacles = false;
                    break;
                }
            }
        }

        if (startCoordinate.getCoordinateX() == targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() < targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateY() + x) > targetCoordinate.getCoordinateY(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY() + x].isHaveChessFigureInCell()) {
                    isWithoutLineObstacles = false;
                    break;
                }
            }
        }

        if (startCoordinate.getCoordinateX() > targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() == targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateX() - x) > targetCoordinate.getCoordinateY(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX() - x][startCoordinate.getCoordinateY()].isHaveChessFigureInCell()) {
                    isWithoutLineObstacles = false;
                    break;
                }
            }
        }

        if (startCoordinate.getCoordinateX() == targetCoordinate.getCoordinateX() && startCoordinate.getCoordinateY() < targetCoordinate.getCoordinateY()) {
            for (int x = 1; (startCoordinate.getCoordinateX() + x) > targetCoordinate.getCoordinateY(); x++) {
                if (chessBoard[startCoordinate.getCoordinateX() + x][startCoordinate.getCoordinateY()].isHaveChessFigureInCell()) {
                    isWithoutLineObstacles = false;
                    break;
                }
            }
        }

        return isWithoutLineObstacles;
    }

    @Override
    public boolean isWithoutCheckToKing(String color, ChessBoardCell[][] chessBoardOriginal) {
        this.chessBoard = cloneChessBoard(chessBoardOriginal);
        this.color = color;

        return isWithoutCheckToKing(getKingCoordinate());
    }


    private boolean isWithoutCheckToKing(Coordinate coordinate) {

        boolean isWithoutDiagonalObstacles = true;
        boolean isWithoutLineObstacles = true;
        boolean isWithoutKnightObstacles = true;
        int checkedCoordinateX = coordinate.getCoordinateX();
        int checkedCoordinateY = coordinate.getCoordinateY();
        while (checkedCoordinateX < 7 && checkedCoordinateY < 7 && isWithoutDiagonalObstacles) {
            checkedCoordinateX += 1;
            checkedCoordinateY += 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("bishop")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || (((coordinate.getCoordinateX() - checkedCoordinateX) == -1 && (coordinate.getCoordinateY() - checkedCoordinateY) == -1)
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("pawn")
                    || chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king")))))) {
                isWithoutDiagonalObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;

            }

        }
        checkedCoordinateX = coordinate.getCoordinateX();
        checkedCoordinateY = coordinate.getCoordinateY();

        while (checkedCoordinateX < 7 && checkedCoordinateY > 0 && isWithoutDiagonalObstacles) {
            checkedCoordinateX += 1;
            checkedCoordinateY -= 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("bishop")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || (((coordinate.getCoordinateX() - checkedCoordinateX) == -1 && (coordinate.getCoordinateY()) - checkedCoordinateY == 1)
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("pawn")
                    || chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king")))))) {
                isWithoutDiagonalObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;
            }

        }
        checkedCoordinateX = coordinate.getCoordinateX();
        checkedCoordinateY = coordinate.getCoordinateY();

        while (checkedCoordinateX > 0 && checkedCoordinateY > 0 && isWithoutDiagonalObstacles) {
            checkedCoordinateX -= 1;
            checkedCoordinateY -= 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("bishop")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || (((coordinate.getCoordinateX() - checkedCoordinateX) == -1 && (coordinate.getCoordinateY() - checkedCoordinateY) == 1)
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("pawn")
                    || chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king")))))) {
                isWithoutDiagonalObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;
            }

        }
        checkedCoordinateX = coordinate.getCoordinateX();
        checkedCoordinateY = coordinate.getCoordinateY();

        while (checkedCoordinateX > 0 && checkedCoordinateY < 7 && isWithoutDiagonalObstacles) {
            checkedCoordinateX -= 1;
            checkedCoordinateY += 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("bishop")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || (((coordinate.getCoordinateX() - checkedCoordinateX) == 1 && (coordinate.getCoordinateY() - checkedCoordinateY) == -1)
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("pawn")
                    || chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king")))))) {
                isWithoutDiagonalObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;
            }
        }

        checkedCoordinateX = coordinate.getCoordinateX();
        checkedCoordinateY = coordinate.getCoordinateY();

        while (checkedCoordinateX < 7 && isWithoutLineObstacles) {
            checkedCoordinateX += 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("rook")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || ((checkedCoordinateX - coordinate.getCoordinateX()) == 1
                    && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king"))))) {
                isWithoutLineObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;
            }
        }

        checkedCoordinateX = coordinate.getCoordinateX();

        while (checkedCoordinateX > 0 && isWithoutLineObstacles) {
            checkedCoordinateX -= 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("rook")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || ((checkedCoordinateX - coordinate.getCoordinateX()) == -1
                    && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king"))))) {
                isWithoutLineObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;
            }
        }

        checkedCoordinateX = coordinate.getCoordinateX();

        while (checkedCoordinateY < 7 && isWithoutLineObstacles) {
            checkedCoordinateY += 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("rook")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || ((checkedCoordinateY - coordinate.getCoordinateY()) == 1
                    && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king"))))) {
                isWithoutLineObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;
            }
        }

        checkedCoordinateY = coordinate.getCoordinateY();

        while (checkedCoordinateY > 0 && isWithoutLineObstacles) {
            checkedCoordinateY -= 1;
            if (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure() != null
                    && (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("rook")
                    || (chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("queen")
                    || ((checkedCoordinateY - coordinate.getCoordinateY()) == -1
                    && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("king"))))) {
                isWithoutLineObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
                break;
            }
        }

        checkedCoordinateX = coordinate.getCoordinateX();
        checkedCoordinateY = coordinate.getCoordinateY();

        if (isWithoutKnightObstacles && (checkedCoordinateX - 1) >= 0 && (checkedCoordinateY - 2) >= 0
                && chessBoard[checkedCoordinateX - 1][checkedCoordinateY - 2].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
        }
        if (isWithoutKnightObstacles && (checkedCoordinateX - 1) >= 0 && (checkedCoordinateY + 2) < 7
                && chessBoard[checkedCoordinateX - 1][checkedCoordinateY + 2].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
        }
        if (isWithoutKnightObstacles && (checkedCoordinateX - 2) >= 0 && (checkedCoordinateY - 1) >= 0
                && chessBoard[checkedCoordinateX - 2][checkedCoordinateY - 1].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
        }
        if (isWithoutKnightObstacles && (checkedCoordinateX - 2) >= 0 && (checkedCoordinateY + 1) < 7
                && chessBoard[checkedCoordinateX - 2][checkedCoordinateY + 1].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
        }

        if (isWithoutKnightObstacles && (checkedCoordinateX + 1) < 7 && (checkedCoordinateY - 2) >= 0
                && chessBoard[checkedCoordinateX + 1][checkedCoordinateY - 2].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));

        }
        if (isWithoutKnightObstacles && (checkedCoordinateX + 1) < 7 && (checkedCoordinateY + 2) < 7
                && chessBoard[checkedCoordinateX + 1][checkedCoordinateY + 2].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));

        }
        if (isWithoutKnightObstacles && (checkedCoordinateX + 2) < 7 && (checkedCoordinateY - 1) >= 0
                && chessBoard[checkedCoordinateX + 2][checkedCoordinateY - 1].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));

        }
        if (isWithoutKnightObstacles && (checkedCoordinateX + 2) < 7 && (checkedCoordinateY + 1) < 7
                && chessBoard[checkedCoordinateX + 2][checkedCoordinateY + 1].getChessFigure() != null
                && chessBoard[checkedCoordinateX][checkedCoordinateY].getChessFigure().getName().equals("knight")) {
            isWithoutKnightObstacles = isActiveColorOfFigure(new Coordinate(checkedCoordinateX, checkedCoordinateY));
        }

        if (!isWithoutDiagonalObstacles || !isWithoutKnightObstacles || !isWithoutLineObstacles) {
            System.out.println("you can not move here? you have check");
            return false;
        }
        return true;
    }

    private Coordinate getKingCoordinate() {
        for (int x = 0; x < chessBoard.length; x++) {
            for (int y = 0; y < chessBoard[x].length; y++) {
                if (chessBoard[x][y].getChessFigure() != null && chessBoard[x][y].getChessFigure().getName().equals("king") && chessBoard[x][y].getChessFigure().getColor().equals(color)) {
                    return new Coordinate(x, y);
                }
            }
        }
        return null;
    }

    private boolean isActiveColorOfFigure(Coordinate coordinate) {

        return chessBoard[coordinate.getCoordinateX()][coordinate.getCoordinateY()].getChessFigure().getColor().equals(color) ? true : false;

    }
    private ChessBoardCell[][] cloneChessBoard(ChessBoardCell[][] chessBoardCells){

        ChessBoardCell [][] chessBoardCellsClone = new ChessBoardCell[8][8];

        for (int x =0; x < chessBoardCells.length; x++) {
            for (int y =0; y < chessBoardCells[x].length; y++) {

                ChessFigure figure;
                 if(chessBoardCells[x][y].getChessFigure() != null){
                     figure = new ChessFigure(chessBoardCells[x][y].getChessFigure());
                 } else {
                     figure = null;
                 }
                chessBoardCellsClone[x][y] = new ChessBoardCell(figure);
            }
        }
        return chessBoardCellsClone;
    }
}
