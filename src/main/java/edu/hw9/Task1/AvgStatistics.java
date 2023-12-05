package edu.hw9.Task1;

import java.util.Arrays;

public class AvgStatistics implements Statistics {
    private final double[] numbers;
    private double result;

    public AvgStatistics(double[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        result = Arrays.stream(numbers).average().orElse(0);
    }

    public double getResult() {
        return result;
    }
}
