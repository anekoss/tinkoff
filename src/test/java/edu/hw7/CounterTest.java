package edu.hw7;

import edu.hw7.Task1.Counter;
import edu.hw7.Task1.CounterThread;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CounterTest {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(50, 100, 50 * 100, 50),
            Arguments.of(0, 100, 0, 0),
            Arguments.of(50, 0, 0, 50),
            Arguments.of(50, 1, 50, 50),
            Arguments.of(-50, 0, 0, 0),
            Arguments.of(0, -50, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void counterTest(int countThreads, int countIncrements, int exceptedCounter, int exceptedCountThreads)
        throws InterruptedException {
        Counter counter = new Counter();
        CounterThread counterThread = new CounterThread(counter);
        assertThat(counterThread.runThreads(countThreads, countIncrements).size()).isEqualTo(exceptedCountThreads);
        assertThat(counterThread.getCounter()).isEqualTo(exceptedCounter);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void countIncrementTest(int countThreads, int countIncrements, int exceptedCounter, int exceptedCountThreads)
        throws InterruptedException {
        Counter counter = Mockito.mock();
        CounterThread counterThread = new CounterThread(counter);
        when(counter.increment()).thenReturn(1);
        counterThread.runThreads(countThreads, countIncrements);
        counterThread.getCounter();
        verify(counter, times(exceptedCountThreads * countIncrements)).increment();
    }
}
