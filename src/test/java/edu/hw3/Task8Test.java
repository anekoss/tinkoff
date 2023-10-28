package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task8Test {
    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 100, 151),
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 100, 151, 2383828, 32, 22),
                List.of(-1, -1, -1, -1),
                Arrays.asList(null, null, 1, 2, 3, null, -100, null),
                new ArrayList<>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void iteratorTest(List<Integer> list) {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);
        int position = list.size() - 1;
        while (position != -1) {
            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(list.get(position));
            position--;
        }
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
