package com.yevheniimakar.task2.counter.impl;

import com.yevheniimakar.task2.counter.Counter;

public class CounterOneImpl implements Counter {

    private int count = 0;

    public synchronized int getCountAndIncrement() {
        return count++;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public synchronized void decrement() {

        count--;
    }
}
