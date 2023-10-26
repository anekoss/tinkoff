package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw3.Task2.clusterize;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of("()()()", new ArrayList<>(List.of("()", "()", "()"))),
            Arguments.of("((()))", new ArrayList<>(List.of("((()))"))),
            Arguments.of("((())())(()(()()))", new ArrayList<>(List.of("((())())", "(()(()()))"))),
            Arguments.of("()  ()()", new ArrayList<>(List.of("()", "()", "()"))),
            Arguments.of("", new ArrayList<String>()),
            Arguments.of(null, new ArrayList<>())
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void clusterizeTest(String value, List excepted) {
        assertThat(clusterize(value)).hasSize(excepted.size()).isEqualTo(excepted);
    }
}
