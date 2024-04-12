package edu.hw8.Task2;

public class Fibonacci implements Runnable {
    private final int n;
    private int fibonacci;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        int previousFibonacci = 1;
        for (int i = 0; i < n; i++) {
            int c = fibonacci + previousFibonacci;
            fibonacci = previousFibonacci;
            previousFibonacci = c;
        }
        if (n <= 0) {
            fibonacci = -1;
        }
    }

    public int getFibonacci() {
        return fibonacci;
    }

    public void setFibonacci() {
        this.fibonacci = 0;
    }
}
