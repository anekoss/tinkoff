package edu.hw9.Task1;

import java.util.Arrays;
import java.util.Map;

public class Stats implements Runnable {
    private final double[] numbers;
    private double sum;
    private double max;
    private double min;
    private double avg;

    public Stats(double[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        max = Arrays.stream(numbers).max().orElse(Double.NEGATIVE_INFINITY);
        sum = Arrays.stream(numbers).sum();
        min = Arrays.stream(numbers).min().orElse(Double.POSITIVE_INFINITY);
        avg = Arrays.stream(numbers).average().orElse(0);
    }

    public Map<String, Double> getStats() {
        return Map.of("sum", sum, "avg", avg, "min", min, "max", max);
    }
}
