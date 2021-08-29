package com.yevheniimakar.controller;

import com.yevheniimakar.set.MathSet;


public class StartedData {

    public static MathSet<Integer> mathSetInteger;
    public static MathSet<Double> mathSetDouble;

    static public void addData(){
        mathSetInteger = new MathSet<>();
        mathSetDouble = new MathSet<>();
        for (int i = 0; i < 20; i++) {
            mathSetInteger.add((int) (Math.random() * 100));
            mathSetDouble.add(((double)((int)(Math.random()*10000))/100));
        }
        mathSetDouble.add(1.2);
        mathSetDouble.add(2.8);
        mathSetDouble.add(5.8);
        mathSetDouble.add(5.4545);
    }
}
