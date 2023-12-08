package edu.hw7.Task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PiCalculatorTest {

    @ParameterizedTest
    @CsvSource({"-1, 2", "1, -2", "0, 0", "0, 2", "-3, -3"})
    void incorrectInputTest(int n, int r) {
        PiCalculator piCalculator = new PiCalculator();
        assertThat(piCalculator.singleThreadPiCalculation(n, r)).isEqualTo(-1);
    }

    @ParameterizedTest
    @CsvSource({"10, 50", "100, 1000", "50, 50", "1000, 50"})
    void piCalculatortest(int n, int r) {
        PiCalculator piCalculator = new PiCalculator();
        assertThat(piCalculator.singleThreadPiCalculation(n, r)).isLessThanOrEqualTo(4.0).isGreaterThan(0);
    }
}
