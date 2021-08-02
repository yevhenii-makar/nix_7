package com.yevheniimakar.module1.level1.task3;

public class Triangle {

    public double getAreaOfTriangle(int pointAX, int pointAY, int pointBX, int pointBY, int pointCX, int pointCY) {

        double sideAB = getSideLength(pointAX, pointAY, pointBX, pointBY);
        double sideBC = getSideLength(pointBX, pointBY, pointCX, pointCY);
        double sideCA = getSideLength(pointAX, pointAY, pointCX, pointCY);

        double p = (sideAB + sideBC + sideCA) / 2;
        double areaOfTriangle = Math.sqrt(p * (p - sideAB) * (p - sideBC) * (p - sideCA));
        return areaOfTriangle;
    }

    public double getSideLength(int firstPointX, int firstPointY, int secondPointX, int secondPointY) {

        int differenceX = firstPointX >= secondPointX ? firstPointX - secondPointX : secondPointX - firstPointX;
        int differenceY = firstPointY >= secondPointY ? firstPointY - secondPointY : secondPointY - firstPointY;
        double sideLength = Math.sqrt((double) (differenceX * differenceX) + (double) (differenceY * differenceY));

        return sideLength;
    }
}
