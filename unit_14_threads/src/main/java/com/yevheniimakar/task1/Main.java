package com.yevheniimakar.task1;

import com.yevheniimakar.task1.massage.Massage;
import com.yevheniimakar.task2.counter.Counter;
import com.yevheniimakar.task2.counter.impl.CounterTwoImpl;

public class Main {
    public static void main(String[] args) {

        int maxSize = 50;

        Counter counter = new CounterTwoImpl(maxSize-1);

        for (int i= 0; i<maxSize;i++) {
            new Thread(new Massage(i,counter)).start();
        }

    }
}
