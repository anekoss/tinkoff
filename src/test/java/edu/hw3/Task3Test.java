package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void freqDictTest(List list, Map excepted) {
        Task3<String> task3 = new Task3<>();
        assertThat(
            task3.freqDict(list)).isEqualTo(excepted);
    }
}

