package com.yevheniimakar.module1.level3.task1;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Task(taskName = "Game of life", order = 5)
public class GameOfLife {
    List<int[][]> listOfStep = new ArrayList<>();
    int[][] currentState;
    int step;

    public void starGame() {

        boolean isGameContinues = true;
        while (isGameContinues) {

            if (listOfStep.size() > 1 && (isAreasEquals(currentState, listOfStep.get(listOfStep.size() - 1)) || isStepAlreadyBeen())) {
                isGameContinues = false;
                System.out.println("Game finish after " + step + " steps");
                break;
            }
            listOfStep.add(currentState);
            printCurrentState();
            currentState = nextStep();
            step += 1;
        }

    }

    public boolean initGame(int areaSideSize, int numberOfLifeCells) {

        if ((areaSideSize * areaSideSize) < numberOfLifeCells) {
            return false;
        }
        currentState = new int[areaSideSize][areaSideSize];
        int numberOfCurrentlifeCells = 0;

        while (numberOfLifeCells > numberOfCurrentlifeCells) {

            currentState[(int) (Math.random() * areaSideSize)][(int) (Math.random() * areaSideSize)] = 1;
            numberOfCurrentlifeCells = 0;
            for (int currentStateFirstLvl[] : currentState) {
                for (int currentStateSecondLvl : currentStateFirstLvl) {
                    numberOfCurrentlifeCells += currentStateSecondLvl;
                }
            }
        }

        return true;
    }

    private int[][] nextStep() {
        int[][] nextStep = new int[currentState.length][currentState.length];
        for (int x = 0; x < nextStep.length; x++) {
            for (int y = 0; y < nextStep.length; y++) {
                nextStep[x][y] = isCellLive(x, y);
            }
        }
        return nextStep;
    }

    private int isCellLive(int coordinateX, int coordinateY) {
        int lineX1 = coordinateX == 0 ? currentState.length - 1 : coordinateX - 1;
        int lineX2 = coordinateX;
        int lineX3 = coordinateX == currentState.length - 1 ? 0 : coordinateX + 1;

        int lineY1 = coordinateY == 0 ? currentState[coordinateX].length - 1 : coordinateY - 1;
        int lineY2 = coordinateY;
        int lineY3 = coordinateY == currentState[coordinateX].length - 1 ? 0 : coordinateY + 1;

        int numberNeighborliveCell = currentState[lineX1][lineY1] + currentState[lineX1][lineY2] + currentState[lineX1][lineY3]
                + currentState[lineX2][lineY1] + currentState[lineX2][lineY3]
                + currentState[lineX3][lineY1] + currentState[lineX3][lineY2] + currentState[lineX3][lineY3];

        if (numberNeighborliveCell == 2 && currentState[coordinateX][coordinateY] == 1) {
            return 1;
        } else if (numberNeighborliveCell == 3) {
            return 1;
        } else {
            return 0;
        }

    }

    private void printCurrentState() {
        System.out.println("");
        System.out.println("___________________");
        for (int x = 0; x < currentState.length; x++) {
            for (int y = 0; y < currentState.length; y++) {
                System.out.print(currentState[x][y]);
            }
            System.out.println("");
        }
        System.out.println("___________________");
        System.out.println("");
    }

    private boolean isAreasEquals(int[][] area1, int[][] area2) {
        boolean isLastAreaEqualsCurrentArea = true;

        for (int x = 0; x < area1.length; x++) {
            if (!Arrays.equals(area1[x], area2[x])) {
                isLastAreaEqualsCurrentArea = false;
                break;
            }
        }
        return isLastAreaEqualsCurrentArea;
    }

    private boolean isStepAlreadyBeen() {
        boolean isStepAlreadyBeen = false;
        for (int[][] step : listOfStep) {

            if (isAreasEquals(step, currentState)) {
                isStepAlreadyBeen = true;
                break;
            }
        }
        return isStepAlreadyBeen;
    }

    @RunTask(runTaskName = "Game of life", order = 0)
    public void starGameOfLife(){
        GameOfLife gameOfLife = new GameOfLife();
        int sideSizeArea;
        int numberOflifeCell;
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("enter size side of area and number of life cell separated by a space: ");
            String[] data = ConsoleReader.getStringFromConsole().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
            if (data.length == 2) {

                sideSizeArea = Integer.parseInt(data[0]);
                numberOflifeCell = Integer.parseInt(data[0]);
                gameOfLife.initGame(sideSizeArea, numberOflifeCell);
                gameOfLife.starGame();
                isContinue = false;
                break;

            }
            System.out.println("You entered wrong data");
        }
    }

}
