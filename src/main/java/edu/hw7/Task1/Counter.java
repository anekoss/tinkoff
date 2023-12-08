package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger atomicInteger;

    public Counter() {
        this.atomicInteger = new AtomicInteger(0);
    }

    public int increment() {
        return atomicInteger.incrementAndGet();
    }

    public int getCount() {
        return atomicInteger.get();
    }

}
