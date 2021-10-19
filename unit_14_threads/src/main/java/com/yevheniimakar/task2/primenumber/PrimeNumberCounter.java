package com.yevheniimakar.task2.primenumber;

import com.yevheniimakar.task2.counter.Counter;

import java.math.BigInteger;
import java.util.List;

public class PrimeNumberCounter implements Runnable {

    private Counter counter;
    private List<Integer> numberList;
    private int countOfPrimeNumber;

    public PrimeNumberCounter(Counter counter, List<Integer> numberList) {
        this.counter = counter;
        this.numberList = numberList;
    }

    @Override
    public void run() {
        int count = counter.getCountAndIncrement();
        while (count < numberList.size()) {
            if (BigInteger.valueOf(numberList.get(count)).isProbablePrime((int) Math.log(numberList.get(count)))) {
                ++countOfPrimeNumber;
                System.out.println(Thread.currentThread().getName() + "  " + numberList.get(count));
            }
            Thread.yield();
            count = counter.getCount();
        }

    }

    public int getCountOfPrimeNumber() {
        return countOfPrimeNumber;
    }
}
