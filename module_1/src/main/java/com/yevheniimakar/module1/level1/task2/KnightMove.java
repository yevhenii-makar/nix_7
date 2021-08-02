package com.yevheniimakar.module1.level1.task2;

public class KnightMove {

    public boolean isCanKnightMove(int startCoordinateX, int startCoordinateY
            , int finishCoordinateX, int finishCoordinateY) {

        int coordinateXDifference = startCoordinateX >= finishCoordinateX ? startCoordinateX - finishCoordinateX : finishCoordinateX - startCoordinateX;
        int coordinateYDifference = startCoordinateY >= finishCoordinateY ? startCoordinateY - finishCoordinateY : finishCoordinateY - startCoordinateY;

        if ((coordinateXDifference == 1 && coordinateYDifference == 2) || (coordinateXDifference == 2 && coordinateYDifference == 1)) {
            return true;
        }
        return false;
    }

}
