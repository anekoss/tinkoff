package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(Arguments.of(
            Map.of(
                "metric1", new double[] {1.0, 2.0, 3.0, 4.0, 5.0},
                "metric2", new double[] {-1123.2, -99999.8, 0.1, 3293.0, 728.0, 0.0},
                "metric3", new double[] {},
                "metric4", new double[] {2832.0},
                "metric5",
                new double[] {1.0, 2.0, 3.0, 4.0, 5.0},
                "metric6",
                new double[] {-1123.2, -99999.8, 0.1, 3293.0, 728.0, 0.0},
                "metric7",
                new double[] {}
            ),
            Map.of(
                "metric1",
                Map.of("sum", 15.0, "avg", 3.0, "min", 1.0, "max", 5.0),
                "metric2",
                Map.of("sum", -97101.9, "avg", -16183.65, "min", -99999.8, "max", 3293.0),
                "metric3",
                Map.of("sum", 0.0, "avg", 0.0, "min", Double.POSITIVE_INFINITY, "max", Double.NEGATIVE_INFINITY),
                "metric4",
                Map.of("sum", 2832.0, "avg", 2832.0, "min", 2832.0, "max", 2832.0),
                "metric5",
                Map.of("sum", 15.0, "avg", 3.0, "min", 1.0, "max", 5.0),
                "metric6",
                Map.of("sum", -97101.9, "avg", -16183.65, "min", -99999.8, "max", 3293.0),
                "metric7",
                Map.of("sum", 0.0, "avg", 0.0, "min", Double.POSITIVE_INFINITY, "max", Double.NEGATIVE_INFINITY)
            )
        ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void pushNumbersTest(Map<String, double[]> numbers, Map<String, Map<String, Double>> excepted)
        throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        StatsCollector statsCollector = new StatsCollector(executorService);
        List<Thread> threadList = new ArrayList<>();
        for (String metricName : numbers.keySet()) {
            Thread thread = new Thread(() -> statsCollector.push(metricName, numbers.get(metricName)));
            threadList.add(thread);
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        for (String metricName : numbers.keySet()) {
            assertThat(statsCollector.getStats(metricName)).isPresent().get().isEqualTo(excepted.get(metricName));
        }
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void countThreadSingleTest(Map<String, double[]> numbers) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        StatsCollector statsCollector = new StatsCollector(executorService);
        final ThreadGroup threadGroup = new ThreadGroup("workers");
        for (String metricName : numbers.keySet()) {
            new Thread(() -> statsCollector.push(metricName, numbers.get(metricName))).start();
            assertThat(threadGroup.activeCount()).isLessThanOrEqualTo(1);
        }
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void countThreadMultiTest(Map<String, double[]> numbers) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        StatsCollector statsCollector = new StatsCollector(executorService);
        final ThreadGroup threadGroup = new ThreadGroup("workers");
        for (String metricName : numbers.keySet()) {
            new Thread(() -> statsCollector.push(metricName, numbers.get(metricName))).start();
            assertThat(threadGroup.activeCount()).isLessThanOrEqualTo(4);
        }
    }

    @Test
    void emptyStatsMapTest() {
        ExecutorService executorService = Mockito.mock(ExecutorService.class);
        StatsCollector statsCollector = new StatsCollector(executorService);
        assertThat(statsCollector.getStats("metric1")).isEqualTo(Optional.empty());
    }
}
