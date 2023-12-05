package edu.hw9.Task1;

import java.util.Arrays;

public class SumStatistics implements Statistics {
    private final double[] numbers;
    private double result;

    public SumStatistics(double[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        result = Arrays.stream(numbers).sum();
    }

    public double getResult() {
        return result;
    }
}
