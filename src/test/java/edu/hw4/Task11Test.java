package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task11.getBiteAnimals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task11Test {
    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of(), List.of()),
            Arguments.of(
                List.of(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 101, 1, true)),
                List.of(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 101, 1, true))
            ),
            Arguments.of(
                List.of(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 4, 130, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 14, 13, 150, true)
                ),
                List.of(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 4, 130, 100, true)
                )
            ),
            Arguments.of(
                List.of(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 105, 1, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.F, 8, 15, 1, false),
                    new Animal("d", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                    new Animal("e", Animal.Type.SPIDER, Animal.Sex.M, 3, 200, 57, true),
                    new Animal("f", Animal.Type.FISH, Animal.Sex.M, 0, 100, 12, false),
                    new Animal("c", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 4, true),
                    new Animal("g", Animal.Type.FISH, Animal.Sex.F, 4, 3, 2, true),
                    new Animal("h", Animal.Type.BIRD, Animal.Sex.M, 10, 800, 5, false),
                    new Animal("i", Animal.Type.DOG, Animal.Sex.F, 15, 54, 2344, true),
                    new Animal("j", Animal.Type.SPIDER, Animal.Sex.M, 8, 4, 234, false)
                ),
                List.of(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 105, 1, true),
                    new Animal("e", Animal.Type.SPIDER, Animal.Sex.M, 3, 200, 57, true)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getBiteAnimalsTest(List<Animal> animals, List<Animal> excepted)
        throws NullAnimalListException, NullAnimalException {
        assertThat(getBiteAnimals(animals)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void getBiteAnimalsExceptionTest(List<Animal> animals, Class excepted) {
        assertThrows(excepted, () -> getBiteAnimals(animals));
    }

    public static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of(null, NullAnimalListException.class),
            Arguments.of(Arrays.asList(null, null), NullAnimalException.class),
            Arguments.of(
                Arrays.asList(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true), null),
                NullAnimalException.class
            )
        );
    }
}
