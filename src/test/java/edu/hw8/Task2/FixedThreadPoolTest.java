package edu.hw8.Task2;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FixedThreadPoolTest {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                Map.of(
                    new Fibonacci(0), -1,
                    new Fibonacci(1), 1,
                    new Fibonacci(2), 1,
                    new Fibonacci(5), 5,
                    new Fibonacci(7), 13,
                    new Fibonacci(9), 34,
                    new Fibonacci(10), 55,
                    new Fibonacci(38), 39088169
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void executeTest(Map<Fibonacci, Integer> fibonacciMap) throws Exception {
        for (int i = 1; i <= 5; i++) {
            try (FixedThreadPool fixedThreadPool = new FixedThreadPool(i)) {
                fixedThreadPool.start();
                for (Fibonacci fibonacci : fibonacciMap.keySet()) {
                    fixedThreadPool.execute(fibonacci);
                }
            }
            for (Fibonacci fibonacci : fibonacciMap.keySet()) {
                assertThat(fibonacci.getFibonacci()).isEqualTo(fibonacciMap.get(fibonacci));
                fibonacci.setFibonacci();
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void countThreadTest(Map<Fibonacci, Integer> fibonacciMap) throws Exception {
        for (int i = 1; i <= 5; i++) {
            int startCountThread = Thread.activeCount();
            int endCountThread;
            try (FixedThreadPool fixedThreadPool = new FixedThreadPool(i)) {
                fixedThreadPool.start();
                for (Fibonacci fibonacci : fibonacciMap.keySet()) {
                    fixedThreadPool.execute(fibonacci);
                }
                endCountThread = Thread.activeCount();
            }
            assertThat(endCountThread - startCountThread).isEqualTo(i);
        }
    }

    @Test
    void closeTest() throws Exception {
        FixedThreadPool fixedThreadPool = new FixedThreadPool(5);
        fixedThreadPool.start();
        fixedThreadPool.close();
        Fibonacci fibonacci = new Fibonacci(-5);
        fixedThreadPool.execute(fibonacci);
        assertThat(fibonacci.getFibonacci()).isEqualTo(0);
    }
}
