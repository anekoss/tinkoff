package edu.hw4;

import edu.hw4.Animal.Animal;
import edu.hw4.Animal.NullAnimalException;
import edu.hw4.Animal.NullAnimalListException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw4.Task1_18.Task1To18.getCountAnimalsType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(),
                Map.of(
                    Animal.Type.CAT,
                    0,
                    Animal.Type.DOG,
                    0,
                    Animal.Type.BIRD,
                    0,
                    Animal.Type.FISH,
                    0,
                    Animal.Type.SPIDER, 0
                )
            ),
            Arguments.of(
                List.of(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true)
                ),
                Map.of(
                    Animal.Type.CAT,
                    2,
                    Animal.Type.DOG,
                    0,
                    Animal.Type.BIRD,
                    0,
                    Animal.Type.FISH,
                    0,
                    Animal.Type.SPIDER, 0
                )
            ),
            Arguments.of(
                List.of(
                    new Animal("a", null, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                ),
                Map.of(
                    Animal.Type.CAT,
                    0,
                    Animal.Type.DOG,
                    1,
                    Animal.Type.BIRD,
                    0,
                    Animal.Type.FISH,
                    0,
                    Animal.Type.SPIDER, 0
                )
            ),
            Arguments.of(List.of(
                new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true),
                new Animal("b", Animal.Type.DOG, Animal.Sex.F, 8, 15, 1, false),
                new Animal("d", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                new Animal("e", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, 57, true),
                new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 100, 12, false),
                new Animal("c", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 4, true),
                new Animal("g", Animal.Type.FISH, Animal.Sex.F, 4, 3, 2, true),
                new Animal("h", Animal.Type.BIRD, Animal.Sex.M, 10, 8, 5, false),
                new Animal("i", Animal.Type.DOG, Animal.Sex.F, 15, 54, 2344, true),
                new Animal("j", Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false)
            ), Map.of(
                Animal.Type.CAT,
                2,
                Animal.Type.DOG,
                2,
                Animal.Type.BIRD,
                2,
                Animal.Type.FISH,
                2,
                Animal.Type.SPIDER, 2
            ))
        );

    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void countAnimalTypeTest(List<Animal> animals, Map<Animal.Type, Integer> countAnimalsType)
        throws NullAnimalListException, NullAnimalException {
        assertThat(getCountAnimalsType(animals)).isEqualTo(countAnimalsType);
    }

    public static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of(null, NullAnimalListException.class),
            Arguments.of(Arrays.asList(null, null), NullAnimalException.class),
            Arguments.of(
                Arrays.asList(null, new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true)),
                NullAnimalException.class
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void countAnimalTypeExceptionTest(List<Animal> animals, Class<Exception> exceptedException) {
        assertThrows(exceptedException, () -> getCountAnimalsType(animals));
    }

}
