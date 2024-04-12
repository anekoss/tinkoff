package edu.hw9.Task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class StatsCollector {
    private final Map<String, Map<String, Double>> statsMetricMap;
    private final ReadWriteLock lock;
    private final ExecutorService executorService;

    public StatsCollector(ExecutorService executorService) {
        this.lock = new ReentrantReadWriteLock();
        this.statsMetricMap = new HashMap<>();
        this.executorService = executorService;
    }

    public void push(@NotNull String metricName, double[] numbers) {
        Future<Map<String, Double>> future = executorService.submit(new Stats(numbers));
        try {
            lock.writeLock().lock();
            statsMetricMap.put(metricName, future.get());
        } catch (ExecutionException e) {
            log.error("execution exception");
            throw new IllegalArgumentException();
        } catch (InterruptedException e) {
            log.error("interrupted exception");
            throw new IllegalArgumentException();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Optional<Map<String, Double>> getStats(String metricName) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return Optional.ofNullable(statsMetricMap.get(metricName));
        } finally {
            readLock.unlock();
        }
    }
}
