package com.yevheniimakar.task1.massage;

import com.yevheniimakar.task2.counter.Counter;

public class Massage implements Runnable {

    private int number;
    private Counter counter;

    public Massage(int number, Counter counter) {
        this.number = number;
        this.counter = counter;
    }

    @Override
    public void run() {
        boolean isContinue = true;
        int count;
        synchronized (counter) {

            while (isContinue) {
                count = counter.getCount();

                if (count == number) {
                    System.out.println("Hello from thread " + number);
                    counter.decrement();
                    counter.notify();
                    isContinue = false;
                } else {
                    try {
                        counter.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
