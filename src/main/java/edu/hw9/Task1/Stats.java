package edu.hw9.Task1;

import java.util.Map;
import java.util.concurrent.Callable;

public class Stats implements Callable<Map<String, Double>> {
    private final double[] numbers;
    private double sum;
    private double max = Double.NEGATIVE_INFINITY;
    private double min = Double.POSITIVE_INFINITY;
    private double avg;

    public Stats(double[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Map<String, Double> call() {
        for (double number : numbers) {
            if (max < number) {
                max = number;
            }
            if (min > number) {
                min = number;
            }
            sum = sum + number;
        }
        if (numbers.length != 0) {
            avg = sum / numbers.length;
        }
        return getStats();
    }

    public Map<String, Double> getStats() {
        return Map.of("sum", sum, "avg", avg, "min", min, "max", max);
    }
}
