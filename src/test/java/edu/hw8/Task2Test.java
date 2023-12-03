package edu.hw8;

import edu.hw8.Task2.Fibonacci;
import edu.hw8.Task2.FixedThreadPool;
import org.junit.jupiter.api.Test;

public class Task2Test {

    @Test
    void test() {
        FixedThreadPool fixedThreadPool = new FixedThreadPool(5);
        fixedThreadPool.start();
        Fibonacci fibonacci = new Fibonacci(1);
        Fibonacci fibonacci1 = new Fibonacci(10);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(fibonacci);
            fixedThreadPool.execute(fibonacci1);
        }
        System.out.println(fibonacci1.getFibonacci());
        System.out.println(fibonacci.getFibonacci());
    }
}
