package com.yevheniimakar.chess;

import com.yevheniimakar.chess.entity.ChessBoardCell;
import com.yevheniimakar.chess.entity.Coordinate;
import com.yevheniimakar.chess.service.ChessBoardCellService;
import com.yevheniimakar.chess.service.ChessFigureService;
import com.yevheniimakar.chess.service.ConsoleReaderService;
import com.yevheniimakar.chess.service.CoordinateService;
import com.yevheniimakar.chess.service.impl.ChessBoardCellServiceImpl;
import com.yevheniimakar.chess.service.impl.ChessFigureServiceImpl;
import com.yevheniimakar.chess.service.impl.ConsoleReaderServiceImpl;
import com.yevheniimakar.chess.service.impl.CoordinateServiceImpl;
import com.yevheniimakar.chess.view.ViewBoard;

import java.util.regex.Pattern;

public class Game {

    private ChessBoardCellService chessBoardCellService;
    private CoordinateService coordinateService;
    private ConsoleReaderService consoleReaderService;
    private ChessBoardCell[][] chessBoard;
    private ChessFigureService chessFigureService;
    private ViewBoard viewBoard;
    private String color = "white";


    public Game() {
        this.chessBoardCellService = new ChessBoardCellServiceImpl();
        this.consoleReaderService = new ConsoleReaderServiceImpl();
        this.chessFigureService = new ChessFigureServiceImpl();
        this.coordinateService = new CoordinateServiceImpl();
        this.viewBoard = new ViewBoard();

    }


    public void StartGame() {
        chessBoard = chessBoardCellService.arrangeFiguresOnBoard();
        boolean continueToPlay = true;

        while (continueToPlay) {

            viewBoard.printChessBoard(chessBoard);
            Coordinate startCoordinate = getCoordinate(true);

            if (chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure() != null
                    && chessBoard[startCoordinate.getCoordinateX()][startCoordinate.getCoordinateY()].getChessFigure().getColor().equals(color)) {
                Coordinate targetCoordinate = getCoordinate(false);

                if (chessFigureService.isCanChessMove(startCoordinate, targetCoordinate, chessBoard)) {
                    chessFigureService.moveFigure(startCoordinate, targetCoordinate, chessBoard);
                    color = color.equals("white") ? "black" : "white";

                } else {
                    if (!chessFigureService.isWithoutCheckToKing(color, chessBoard)) {
                        System.out.println("You have chack.\n Are you capitulate [yes(y)/no(n)]");
                        while (true) {
                            String answerString = consoleReaderService.getStringFromConsole();
                            if (answerString.equalsIgnoreCase("yes") || answerString.equalsIgnoreCase("y")) {
                                continueToPlay = false;
                                break;
                            } else if (answerString.equalsIgnoreCase("no") || answerString.equalsIgnoreCase("n")) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Wrong move. Try again");
                    }
                }
            } else {
                System.out.println("is empty cell, try again");

            }
        }

    }

    private Coordinate getCoordinate(boolean isChoiceFigure) {
        String massage = isChoiceFigure ? "Choice " + color + " figure \n" : "Choice coordinate to move \n";

        boolean isNotCoordinate = true;
        Pattern pattern1 = Pattern.compile("^[A,a,B,b,C,c,D,d,E,e,F,f,G,g,H,h][1,2,3,4,5,6,7,8]");
        Pattern pattern2 = Pattern.compile("^[1,2,3,4,5,6,7,8][A,a,B,b,C,c,D,d,E,e,F,f,G,g,H,h]");
        while (isNotCoordinate) {
            System.out.println(massage + "enter coordinates (example \"B3\" or \"3b\"): ");
            String stringFromConsole = consoleReaderService.getStringFromConsole();
            if (pattern1.matcher(stringFromConsole).matches() || pattern2.matcher(stringFromConsole).matches()) {
                isNotCoordinate = false;
                Coordinate coordinate = coordinateService.getCoordinateFromString(stringFromConsole);
                return coordinate;
            }

        }
        return null;
    }

}
