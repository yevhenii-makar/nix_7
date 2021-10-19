package com.yevheniimakar.task2.counter.impl;

import com.yevheniimakar.task2.counter.Counter;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterTwoImpl implements Counter {

    private volatile AtomicInteger count;

    public CounterTwoImpl(int count) {
        this.count = new AtomicInteger(count);
    }
    public CounterTwoImpl() {
        this.count = new AtomicInteger(0);
    }

    public int getCountAndIncrement() {
        return count.getAndIncrement();
    }

    @Override
    public int getCount() {
        return count.get();
    }

    @Override
    public void decrement() {
        count.decrementAndGet();
    }
}
