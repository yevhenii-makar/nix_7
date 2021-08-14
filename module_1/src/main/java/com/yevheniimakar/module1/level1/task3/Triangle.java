package com.yevheniimakar.module1.level1.task3;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;

@Task(taskName = "Area of triangle", order = 3)
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
    @RunTask (runTaskName = "Area of triangle")
    public void startAreaOfTriangle(){
        int coordinateAX;
        int coordinateAY;
        int coordinateBX;
        int coordinateBY;
        int coordinateCX;
        int coordinateCY;
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("enter coordinate point A - X and Y separated by a space: ");
            String[] coordinateA = ConsoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
            if (coordinateA.length == 2) {
                System.out.print("enter coordinate point B - X and Y separated by a space: ");
                String[] coordinateB = ConsoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
                if (coordinateB.length == 2) {
                    System.out.print("enter coordinate point C - X and Y separated by a space: ");
                    String[] coordinateC = ConsoleReader.getStringFromConsole().trim().replaceAll("[^0-9 ]", "").split("[ \\t\\n\\x0B\\f\\r]");
                    if (coordinateC.length == 2) {
                        coordinateAX = Integer.parseInt(coordinateA[0]);
                        coordinateAY = Integer.parseInt(coordinateA[1]);
                        coordinateBX = Integer.parseInt(coordinateB[0]);
                        coordinateBY = Integer.parseInt(coordinateB[1]);
                        coordinateCX = Integer.parseInt(coordinateC[0]);
                        coordinateCY = Integer.parseInt(coordinateC[1]);
                        System.out.println("Area of triangle = " + new Triangle().getAreaOfTriangle(coordinateAX, coordinateAY, coordinateBX, coordinateBY, coordinateCX, coordinateCY));

                        isContinue = false;
                        break;
                    }
                }
            }
            System.out.println("You entered wrong data");
        }
    }
}
