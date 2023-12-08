package edu.hw7.Task1;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(ConcurrentTestRunner.class)
public class CounterTest {

    private final Counter counter = new Counter();
    private final static int THREAD_COUNT = 57;

    @Test
    @ThreadCount(THREAD_COUNT)
    public void incrementTest() {
        counter.increment();
    }

    @After
    public void checkResultTest() {
        assertThat(counter.getCount()).isEqualTo(THREAD_COUNT);
    }

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
    void countIncrementTest(int countThreads, int countIncrements, int ignoredExceptedCounter, int exceptedCountThreads)
        throws InterruptedException {
        Counter counter = Mockito.mock();
        CounterThread counterThread = new CounterThread(counter);
        when(counter.increment()).thenReturn(1);
        counterThread.runThreads(countThreads, countIncrements);
        counterThread.getCounter();
        verify(counter, times(exceptedCountThreads * countIncrements)).increment();
    }
}
