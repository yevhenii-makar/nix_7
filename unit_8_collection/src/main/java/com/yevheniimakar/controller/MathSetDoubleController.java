package com.yevheniimakar.controller;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.set.MathSet;

import java.util.Arrays;


@Task (taskName = "Math Set Double", order = 0)
public class MathSetDoubleController {

    @RunTask (runTaskName = "add number(double)", order = 0)
    public void addNumber() {
        System.out.println(StartedData.mathSetDouble);
        System.out.print("Enter number to add: ");
        StartedData.mathSetDouble.add(ConsoleReader.doubleReader());
        System.out.println(StartedData.mathSetDouble);
    }

    @RunTask (runTaskName = "add number array(double)", order = 1)
    public void addNumberArray() {
        System.out.println(StartedData.mathSetDouble);
        System.out.print("Enter numbers via space and use point  to add: ");
        StartedData.mathSetDouble.add(ConsoleReader.doubleArrayReader());
        System.out.println(StartedData.mathSetDouble);
    }

    @RunTask (runTaskName = "sort desc", order = 2)
    public void sortNumberDesc() {
        System.out.println("Before sorting : ");
        System.out.println(StartedData.mathSetDouble);
        StartedData.mathSetDouble.sortDesc();
        System.out.println("After Sorting");
        System.out.println(StartedData.mathSetDouble);
        System.out.println("press any key to continue");
        ConsoleReader.getStringFromConsole();
    }

    @RunTask (runTaskName = "sort asc", order = 3)
    public void sortNumberAsc() {
        System.out.println("Before sorting : ");
        System.out.println(StartedData.mathSetDouble);
        StartedData.mathSetDouble.sortAsc();
        System.out.println("After Sorting");
        System.out.println(StartedData.mathSetDouble);
        System.out.println("press any key to continue");
        ConsoleReader.getStringFromConsole();
    }

    @RunTask (runTaskName = "get number by index", order = 4)
    public void getByIndex() {
        System.out.println(StartedData.mathSetDouble);
        System.out.print("get index: ");
        System.out.println(StartedData.mathSetDouble.get(ConsoleReader.integerReader()));
    }

    @RunTask (runTaskName = "get max number", order = 5)
    public void getMax() {
        System.out.println(StartedData.mathSetDouble);
        System.out.println("maximum value: " + StartedData.mathSetDouble.getMax());
        System.out.println("press any key to continue");
        ConsoleReader.getStringFromConsole();
    }

    @RunTask (runTaskName = "get min number", order = 6)
    public void getMin() {
        System.out.println(StartedData.mathSetDouble);
        System.out.println("minimum value: " + StartedData.mathSetDouble.getMin());
        System.out.println("press any key to continue");
        ConsoleReader.getStringFromConsole();
    }

    @RunTask (runTaskName = "get average number", order = 7)
    public void getAverage() {
        System.out.println(StartedData.mathSetDouble);
        System.out.println("average value: " + StartedData.mathSetDouble.getAverage());
        System.out.println("press any key to continue");
        ConsoleReader.getStringFromConsole();
    }

    @RunTask (runTaskName = "get Median number", order = 8)
    public void getMedian() {
        System.out.println(StartedData.mathSetDouble);
        System.out.println("median value: " + StartedData.mathSetDouble.getMedian());
        System.out.println("press any key to continue");
        ConsoleReader.getStringFromConsole();
    }

    @RunTask (runTaskName = "intersection number", order = 9)
    public void intersection() {
        System.out.println(StartedData.mathSetDouble);
        System.out.print("Enter numbers to add via space : ");
        Double[] numberArr = ConsoleReader.doubleArrayReader();
        MathSet<Double> ms = new MathSet<>();
        for (int i = 0; i < numberArr.length; i++) {
            ms.add(numberArr[i]);
        }
        System.out.println("Your MathSet: " + ms);
        System.out.println("result: ");
        StartedData.mathSetDouble.intersection(ms);
        System.out.println(StartedData.mathSetDouble);
    }

    @RunTask (runTaskName = "get array number by start/end index", order = 10)
    public void arrayBetweenIndex() {
        System.out.println(StartedData.mathSetDouble);
        System.out.print("Enter numbers via space to add: ");
        Integer[] numberArr = ConsoleReader.integerArrayReader();

        System.out.println(Arrays.toString(StartedData.mathSetDouble.toArray(numberArr[0], numberArr[1])));
    }

}
