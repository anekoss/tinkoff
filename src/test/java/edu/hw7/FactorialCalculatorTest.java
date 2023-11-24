package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw7.Task2.FactorialCalculator.getFactorialOfNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class FactorialCalculatorTest {

    @ParameterizedTest
    @CsvSource({"0, 1",
        "1, 1",
        "-1, -1",
        "-100,-1",
        "2, 2",
        "5, 120",
        "10, 3628800"})
    void getFactorialOfNumberTest(int number, int exceptedFactorial) {
        assertThat(getFactorialOfNumber(number)).isEqualTo(exceptedFactorial);
    }
}
