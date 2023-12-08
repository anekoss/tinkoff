package edu.hw7.Task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ThreadPiCalculatorTest {

    @ParameterizedTest
    @CsvSource({"-1, 2, 1", "1, -2, 1", "0, 0, 0", "0, 2, 3", "-3, -3, -3"})
    void incorrectInputTest(int radius, int countIteration, int countThreads) throws InterruptedException {
        ThreadPiCalculator threadPiCalculator = new ThreadPiCalculator();
        assertThat(threadPiCalculator.getPi(radius, countIteration, countThreads)).isEqualTo(-1);
    }

    @ParameterizedTest
    @CsvSource({"100, 100, 1",
        "100, 100, 2",
        "100, 100, 3",
        "100, 100, 4"})
    void piThreadCalculatortest(int radius, int countIteration, int countThreads) throws InterruptedException {
        ThreadPiCalculator threadPiCalculator = new ThreadPiCalculator();
        assertThat(threadPiCalculator.getPi(radius, countIteration, countThreads)).isLessThanOrEqualTo(4.0)
            .isGreaterThan(0);
        assertThat(threadPiCalculator.getTotalCount()).isEqualTo(countIteration);
        assertThat(threadPiCalculator.getThreads().size()).isEqualTo(countThreads);
    }

}
