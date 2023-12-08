package edu.hw9.Task1;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsTest {
    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                new double[] {1.0, 2.0, 3.0, 4.0, 5.0},
                Map.of("sum", 15.0, "avg", 3.0, "min", 1.0, "max", 5.0)
            ),
            Arguments.of(
                new double[] {-1123.2, -99999.8, 0.1, 3293.0, 728.0, 0.0},
                Map.of("sum", -97101.9, "avg", -16183.65, "min", -99999.8, "max", 3293.0)
            ),
            Arguments.of(
                new double[] {},
                Map.of("sum", 0.0, "avg", 0.0, "min", Double.POSITIVE_INFINITY, "max", Double.NEGATIVE_INFINITY)
            ),
            Arguments.of(
                new double[] {2832.0},
                Map.of("sum", 2832.0, "avg", 2832.0, "min", 2832.0, "max", 2832.0)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getStatsTest(double[] numbers, Map<String, Double> excepted) throws ExecutionException, InterruptedException {
        FutureTask<Map<String, Double>> stats = new FutureTask<>(new Stats(numbers));
        stats.run();
        assertThat(stats.get()).isEqualTo(excepted);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getStatsThreadTest(double[] numbers, Map<String, Double> excepted)
        throws ExecutionException, InterruptedException {
        FutureTask<Map<String, Double>> stats = new FutureTask<>(new Stats(numbers));
        Thread thread = new Thread(stats);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertThat(stats.get()).isEqualTo(excepted);
    }
}
