package edu.hw9.Task1;

import java.util.Arrays;

public class MaxStatistics implements Statistics {
    private final double[] numbers;
    private double result;

    public MaxStatistics(double[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        result = Arrays.stream(numbers).max().orElse(0);
    }

    public double getResult() {
        return result;
    }
}
