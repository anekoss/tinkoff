package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPiCalculator {
    private final AtomicInteger circleCount;
    private final AtomicInteger totalCount;
    private final double coefficient = 4.0;
    private List<Thread> threads;

    public ThreadPiCalculator() {
        this.circleCount = new AtomicInteger(0);
        this.totalCount = new AtomicInteger(0);
    }

    public void multiThreadPiCalculation(int radius, int countIteration, int countThreads) {
        threads = new ArrayList<>();
        for (int i = 0; i < countThreads; i++) {
            Thread thread = new Thread(() -> {
                while (totalCount.get() != countIteration) {
                    double x = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    double y = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    if (x * x + y * y <= radius * radius) {
                        circleCount.incrementAndGet();
                    }
                    totalCount.incrementAndGet();
                }
            });
            threads.add(thread);
            thread.start();
        }
    }

    public double getPi() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
        return coefficient * circleCount.get() / totalCount.get();
    }

}
