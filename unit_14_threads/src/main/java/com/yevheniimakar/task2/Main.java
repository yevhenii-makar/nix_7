package com.yevheniimakar.task2;

import com.yevheniimakar.task2.counter.Counter;
import com.yevheniimakar.task2.counter.impl.CounterOneImpl;
import com.yevheniimakar.task2.counter.impl.CounterTwoImpl;
import com.yevheniimakar.task2.primenumber.PrimeNumberCounter;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Counter counter = new CounterTwoImpl();
//        Counter counter = new CounterOneImpl();
        Integer[] ints = {1, 5, 7, 8, 9, 97, 89};
        List<Integer> integerList = Arrays.asList(ints);
        PrimeNumberCounter primeNumberCounter1 = new PrimeNumberCounter(counter, integerList);
        PrimeNumberCounter primeNumberCounter2 = new PrimeNumberCounter(counter, integerList);

        Thread thread1 = new Thread(primeNumberCounter1);
        Thread thread2 = new Thread(primeNumberCounter2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(primeNumberCounter1.getCountOfPrimeNumber()+primeNumberCounter2.getCountOfPrimeNumber());

    }
}
