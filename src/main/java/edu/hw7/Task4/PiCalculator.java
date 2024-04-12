package edu.hw7.Task4;

import java.util.Random;

public class PiCalculator {
    private int totalCount;
    private int circleCount;
    private static final double COEFFICIENT = 4.0;
    private static final Random RANDOM = new Random();

    public PiCalculator() {
    }

    public double singleThreadPiCalculation(int n, int r) {
        if (n <= 0 || r <= 0) {
            return -1;
        }
        totalCount = 0;
        circleCount = 0;
        for (int i = 0; i < n; i++) {
            double x = RANDOM.nextDouble(-r, r);
            double y = RANDOM.nextDouble(-r, r);
            if (x * x + y * y <= r * r) {
                circleCount++;
            }
            totalCount++;
        }
        return COEFFICIENT * circleCount / totalCount;
    }
}
