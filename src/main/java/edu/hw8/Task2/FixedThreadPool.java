package edu.hw8.Task2;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> blockingQueue;
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
                        log.error("Thread interrupt");
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
            log.error("Thread interrupt");
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
