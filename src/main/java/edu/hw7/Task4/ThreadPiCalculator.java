package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPiCalculator {
    private AtomicInteger circleCount;
    private AtomicInteger totalCount;
    private static final double COEFFICIENT = 4.0;
    private List<Thread> threads;

    public ThreadPiCalculator() {
    }

    public double getPi(int radius, int countIteration, int countThreads) throws InterruptedException {
        if (countThreads <= 0 || countIteration <= 0 || radius <= 0) {
            return -1;
        }
        init();
        multiThreadPiCalculation(radius, countIteration, countThreads);
        for (Thread thread : threads) {
            thread.join();
        }
        return COEFFICIENT * circleCount.get() / totalCount.get();
    }

    public int getTotalCount() {
        return totalCount.get();
    }

    public List<Thread> getThreads() {
        return threads;
    }

    private void init() {
        threads = new ArrayList<>();
        circleCount = new AtomicInteger(0);
        totalCount = new AtomicInteger(0);
    }

    private void multiThreadPiCalculation(int radius, int countIteration, int countThreads) {
        for (int i = 0; i < countThreads; i++) {
            Thread thread = new Thread(() -> {
                while (totalCount.get() < countIteration) {
                    totalCount.incrementAndGet();
                    double x = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    double y = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    if (x * x + y * y <= radius * radius) {
                        circleCount.incrementAndGet();
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
    }

}
