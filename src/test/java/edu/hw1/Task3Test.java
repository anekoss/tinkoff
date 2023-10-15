package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw1.Task3.isNestable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @ParameterizedTest
    @DisplayName("OK should return correct result")
    @MethodSource("provideDataForTest")
    void correctInputTest(int[] a1, int[] a2, boolean expected) {
        assertThat(isNestable(a1, a2)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}, true),
            Arguments.of(new int[] {3, 1}, new int[] {4, 0}, true),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}, false),
            Arguments.of(new int[] {3, 1}, new int[] {2, 0}, true),
            Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}, false),
            Arguments.of(new int[] {8}, new int[] {8}, false),
            Arguments.of(new int[] {8, 4}, new int[] {8, 5}, false),
            Arguments.of(new int[] {9, 4}, new int[] {8, 5}, false),
            Arguments.of(new int[] {}, new int[] {}, false),
            Arguments.of(new int[] {}, new int[] {1, 2, 3}, true),
            Arguments.of(new int[] {5, 8, 10, 246}, new int[] {}, true)

            );
    }
}
