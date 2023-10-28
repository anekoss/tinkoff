package edu.hw3;

import edu.hw3.Task3.Task3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of("", "", "", "meow", "meow", ""), Map.of("", 4, "meow", 2))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void freqDictTest(List<String> list, Map<String, Integer> excepted) {
        assertThat(new Task3<String>().freqDict(list)).isEqualTo(excepted);
    }

    @Test
    void freqDictNullTest() {
        Map<String, Integer> exceptedMap = new HashMap<>();
        exceptedMap.put(null, 3);
        exceptedMap.put("hi", 1);
        assertThat(new Task3<String>().freqDict(Arrays.asList(null, null, null, "hi"))).isEqualTo(exceptedMap);
        assertThat(new Task3<String>().freqDict(List.of())).isEmpty();
    }

    @Test
    void freqDictIntTest() {
        assertThat(new Task3<Integer>().freqDict(Arrays.asList(1, 1, 2, 2))).isEqualTo(Map.of(1, 2, 2, 2));
    }
}

