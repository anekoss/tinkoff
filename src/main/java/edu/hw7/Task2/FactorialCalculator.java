package edu.hw7.Task2;

import java.util.stream.IntStream;

public class FactorialCalculator {
    private FactorialCalculator() {
    }

    public static int getFactorialOfNumber(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        return IntStream.range(1, number + 1).parallel().reduce((left, right) -> left * right).orElse(-1);
    }
}
