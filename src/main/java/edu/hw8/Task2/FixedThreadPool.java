package edu.hw8.Task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private BlockingQueue<Runnable> blockingQueue;
    private final Thread[] threads;

    public FixedThreadPool(int countThreads) {
        this.blockingQueue = new LinkedBlockingQueue<>(countThreads);
        threads = new Thread[countThreads];

    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Runnable task = blockingQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            );
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() throws Exception {
        Arrays.stream(threads).forEach(Thread::interrupt);
    }
}
