package edu.hw9.Task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {
    Map<String, Stats> statsMetricMap;
    private final ReadWriteLock lock;

    private final ExecutorService executorService;

    public StatsCollector(ExecutorService executorService) {
        this.lock = new ReentrantReadWriteLock();
        statsMetricMap = new HashMap<>();
        this.executorService = executorService;
    }

    public void push(String metricName, double[] numbers) {
        executorService.submit(() -> {
                Stats stats = new Stats(numbers);
                lock.writeLock();
                try {
                    statsMetricMap.put(metricName, stats);
                } finally {
                    lock.readLock();
                }
            }
        );
    }

    public Optional<Map<String, Double>> getStats(String metricName) {
        return Optional.of(statsMetricMap.get(metricName).getStats());
    }

}
