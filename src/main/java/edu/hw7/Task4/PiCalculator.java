package edu.hw7.Task4;

import java.util.Random;

public class PiCalculator {
    private int totalCount;
    private int circleCount;
    private static final Random RANDOM = new Random();

    public PiCalculator() {
    }

    void singleThreadPiCalculation(int n, int r) {
        for (int i = 0; i < n; i++) {
            double x = RANDOM.nextDouble(-r, r);
            double y = RANDOM.nextDouble(-r, r);
            if (x * x + y * y <= r * r) {
                circleCount++;
            }
            totalCount++;
        }
    }
}
