package com.yevheniimakar.module1;

import com.yevhenii.makar.ConsoleDialog;
import com.yevheniimakar.module1.console.ConsoleReader;
import com.yevheniimakar.module1.level1.task1.UniqueNumbers;
import com.yevheniimakar.module1.level1.task2.KnightMove;
import com.yevheniimakar.module1.level1.task3.Triangle;
import com.yevheniimakar.module1.level2.task1.Bracket;
import com.yevheniimakar.module1.level3.task1.GameOfLife;


public class Main {
    public static void main(String[] args) {
        ConsoleDialog consoleDialog = new ConsoleDialog("com.yevheniimakar.module1");
        consoleDialog.run();



//
//        boolean continueApp = true;
//        ConsoleReader consoleReader = new ConsoleReader();
//
//        while (continueApp) {
//            System.out.println("1 - UniqueNumbers from arr");
//            System.out.println("2 - CanKnightMove");
//            System.out.println("3 - Area of triangle");
//            System.out.println("4 - Brackets");
//            System.out.println("5 - game of life");
//            System.out.println("6 - exit");
//            System.out.print("Your choice: ");
//            String choice = consoleReader.getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]", "").replace("[^1-6]", "");
//
//            switch (choice) {
//                case "1": {
//                    System.out.println("Your choice uniqueNumbers from arr");
//                    boolean isContinue = true;
//                    while (isContinue) {
//                        System.out.print("enter numbers separated by a space: ");
//                        String numberString = consoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "");
//                        String[] numberArr = numberString.split("[ \\t\\n\\x0B\\f\\r]");
//                        if (numberArr.length > 0) {
//                            int[] numArr = new int[numberArr.length];
//                            for (int i = 0; i < numArr.length; i++) {
//                                numArr[i] = Integer.parseInt(numberArr[i]);
//                            }
//                            System.out.println(new UniqueNumbers().getNumberOfUniqueNumbers(numArr));
//                            isContinue = false;
//                            break;
//                        }
//                        System.out.println("You entered wrong data");
//                    }
//                }
//                break;
//
//                case "2": {
//                    System.out.println("Your choice CanKnightMove");
//                    int startCoordinateX;
//                    int startCoordinateY;
//                    int targetCoordinateX;
//                    int targetCoordinateY;
//                    boolean isContinue = true;
//                    while (isContinue) {
//                        System.out.print("enter start coordinate X and Y separated by a space: ");
//                        String[] startCoordinate = consoleReader.getStringFromConsole().replaceAll("[^0-9 ]", "").trim().split("[ \\t\\n\\x0B\\f\\r]");
//                        if (startCoordinate.length == 2) {
//                            System.out.print("enter target coordinate X and Y separated by a space: ");
//                            String[] targetCoordinate = consoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
//                            if (startCoordinate.length == 2) {
//                                startCoordinateX = Integer.parseInt(startCoordinate[0]);
//                                startCoordinateY = Integer.parseInt(startCoordinate[1]);
//                                targetCoordinateX = Integer.parseInt(targetCoordinate[0]);
//                                targetCoordinateY = Integer.parseInt(targetCoordinate[1]);
//
//                                System.out.println("Knight is " + (new KnightMove().isCanKnightMove(startCoordinateX, startCoordinateY, targetCoordinateX, targetCoordinateY) ? " can move" : "can`t move"));
//                                isContinue = false;
//                                break;
//                            }
//                        }
//                        System.out.println("You entered wrong data");
//                    }
//                }
//                break;
//                case "3": {
//                    System.out.println("Your choice Area of triangle");
//                    int coordinateAX;
//                    int coordinateAY;
//                    int coordinateBX;
//                    int coordinateBY;
//                    int coordinateCX;
//                    int coordinateCY;
//                    boolean isContinue = true;
//                    while (isContinue) {
//                        System.out.print("enter coordinate point A - X and Y separated by a space: ");
//                        String[] coordinateA = consoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
//                        if (coordinateA.length == 2) {
//                            System.out.print("enter coordinate point B - X and Y separated by a space: ");
//                            String[] coordinateB = consoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
//                            if (coordinateB.length == 2) {
//                                System.out.print("enter coordinate point C - X and Y separated by a space: ");
//                                String[] coordinateC = consoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
//                                if (coordinateC.length == 2) {
//                                    coordinateAX = Integer.parseInt(coordinateA[0]);
//                                    coordinateAY = Integer.parseInt(coordinateA[1]);
//                                    coordinateBX = Integer.parseInt(coordinateB[0]);
//                                    coordinateBY = Integer.parseInt(coordinateB[1]);
//                                    coordinateCX = Integer.parseInt(coordinateC[0]);
//                                    coordinateCY = Integer.parseInt(coordinateC[1]);
//                                    System.out.println("Area of triangle = " + new Triangle().getAreaOfTriangle(coordinateAX, coordinateAY, coordinateBX, coordinateBY, coordinateCX, coordinateCY));
//
//                                    isContinue = false;
//                                    break;
//                                }
//                            }
//                        }
//                        System.out.println("You entered wrong data");
//                    }
//                }
//                break;
//
//                case "4": {
//                    System.out.println("Your choice bracket");
//                    int coordinateCY;
//                    boolean isContinue = true;
//                    while (isContinue) {
//                        System.out.print("enter string use only bracket  (){}[]: ");
//                        String stringBracket = consoleReader.getStringFromConsole().replaceAll("[^\\{\\}\\(\\)\\(\\)]", "");
//                        if (stringBracket.length() > 0) {
//
//                            System.out.println("Braket string is " + (new Bracket().isBracketStringValid(stringBracket) ? " valid" : "not valid"));
//                            isContinue = false;
//                            break;
//                        }
//                        System.out.println("You entered wrong data");
//                    }
//                }
//                break;
//                case "5": {
//                    System.out.println("Your choice game of life.");
//                    GameOfLife gameOfLife = new GameOfLife();
//                    int sideSizeArea;
//                    int numberOflifeCell;
//                    boolean isContinue = true;
//                    while (isContinue) {
//                        System.out.print("enter size side of area and number of life cell separated by a space: ");
//                        String[] data = consoleReader.getStringFromConsole().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
//                        if (data.length == 2) {
//
//                            sideSizeArea = Integer.parseInt(data[0]);
//                            numberOflifeCell = Integer.parseInt(data[0]);
//                            gameOfLife.initGame(sideSizeArea, numberOflifeCell);
//                            gameOfLife.starGame();
//                            isContinue = false;
//                            break;
//
//                        }
//                        System.out.println("You entered wrong data");
//                    }
//
//                }
//                break;
//
//                case "6": {
//                    continueApp = false;
//                }
//                break;
//
//            }
//        }
    }
}
