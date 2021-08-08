package com.yevheniimakar.module1.level1.task2;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;

public class KnightMove {

    @Task(taskName = "Can knight move", order = 2)
    public boolean isCanKnightMove(int startCoordinateX, int startCoordinateY
            , int finishCoordinateX, int finishCoordinateY) {

        int coordinateXDifference = startCoordinateX >= finishCoordinateX ? startCoordinateX - finishCoordinateX : finishCoordinateX - startCoordinateX;
        int coordinateYDifference = startCoordinateY >= finishCoordinateY ? startCoordinateY - finishCoordinateY : finishCoordinateY - startCoordinateY;

        if ((coordinateXDifference == 1 && coordinateYDifference == 2) || (coordinateXDifference == 2 && coordinateYDifference == 1)) {
            return true;
        }
        return false;
    }
    @RunTask
    public void runKnightMove(){
        int startCoordinateX;
        int startCoordinateY;
        int targetCoordinateX;
        int targetCoordinateY;
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("enter start coordinate X and Y separated by a space: ");
            String[] startCoordinate = ConsoleReader.getStringFromConsole().replaceAll("[^0-9 ]", "").trim().split("[ \\t\\n\\x0B\\f\\r]");
            if (startCoordinate.length == 2) {
                System.out.print("enter target coordinate X and Y separated by a space: ");
                String[] targetCoordinate = ConsoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
                if (startCoordinate.length == 2) {
                    startCoordinateX = Integer.parseInt(startCoordinate[0]);
                    startCoordinateY = Integer.parseInt(startCoordinate[1]);
                    targetCoordinateX = Integer.parseInt(targetCoordinate[0]);
                    targetCoordinateY = Integer.parseInt(targetCoordinate[1]);

                    System.out.println("Knight is " + (new KnightMove().isCanKnightMove(startCoordinateX, startCoordinateY, targetCoordinateX, targetCoordinateY) ? " can move" : "can`t move"));
                    isContinue = false;
                    break;
                }
            }
            System.out.println("You entered wrong data");
        }
    }

}
