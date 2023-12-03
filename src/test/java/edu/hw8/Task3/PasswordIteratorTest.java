package edu.hw8.Task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw8.Task3.PasswordGenerator.SYMBOLS;
import static edu.hw8.Task3.PasswordIterator.countCombination;
import static edu.hw8.Task3.PasswordIterator.nextPassword;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordIteratorTest {

    @ParameterizedTest
    @CsvSource({"2,2,4", "100,100,10000", "-1, 100, 0", "-1,-1,0", "100,-1,0", "0,100,0",})
    void countCombinationTest(int combinationLength, int sizeAlpabet, int excepted) {
        assertThat(countCombination(combinationLength, sizeAlpabet)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"1, b", "0, a", "100, Mb", "373, bg", "20000, Kmf", "-100, ''", "37473474, 3IoHc"})
    void nextPasswordIteratorTest(int numberNextPassword, String excepted) {
        assertThat(nextPassword(numberNextPassword, SYMBOLS)).isEqualTo(excepted);
    }
}
