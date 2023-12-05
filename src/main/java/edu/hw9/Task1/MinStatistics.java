package edu.hw9.Task1;

import java.util.Arrays;

public class MinStatistics implements Statistics {
    private final double[] numbers;
    private double result;

    public MinStatistics(double[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        result = Arrays.stream(numbers).min().orElse(0);
    }

    public double getResult() {
        return result;
    }
}
