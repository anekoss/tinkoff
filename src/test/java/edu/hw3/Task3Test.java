package edu.hw3;

import org.junit.jupiter.params.provider.Arguments;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Task3 {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb": 2, "a": 2),
            Arguments.of("((()))", new ArrayList<>(List.of("((()))"))),
            Arguments.of("((())())(()(()()))", new ArrayList<>(List.of("((())())", "(()(()()))"))),
            Arguments.of("()  ()()", new ArrayList<>(List.of("()", "()", "()"))),
            Arguments.of("", new ArrayList<String>()),
            Arguments.of(null, new ArrayList<>())
        );
    }
}

