package edu.hw7.Task1;

import java.util.ArrayList;
import java.util.List;

public class CounterThread {
    private List<Thread> threads = new ArrayList<>();
    private final Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    public List<Thread> runThreads(int countThreads, int countIncrements) {
        this.threads = new ArrayList<>();
        for (int i = 0; i < countThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < countIncrements; j++) {
                    counter.increment();
                }
            });
            threads.add(thread);
            thread.start();
        }
        return threads;
    }

    public int getCounter() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
        return counter.getCount();
    }
}
