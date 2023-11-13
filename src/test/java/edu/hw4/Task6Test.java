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
import static edu.hw4.Task1_18.Task1To18.getTypeMaxWeightAnimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of(), Map.of()),
            Arguments.of(
                List.of(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 150, true)
                ),
                Map.of(
                    Animal.Type.DOG,
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 150, true),
                    Animal.Type.CAT,
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true)
                )

            ),
            Arguments.of(
                List.of(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", null, Animal.Sex.M, 12, 13, 150, true)
                ),
                Map.of(
                    Animal.Type.CAT,
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true)
                )

            ),

            Arguments.of(
                List.of(
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
                ),
                Map.of(
                    Animal.Type.DOG,
                    new Animal("i", Animal.Type.DOG, Animal.Sex.F, 15, 54, 2344, true),
                    Animal.Type.CAT,
                    new Animal("d", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                    Animal.Type.SPIDER,
                    new Animal("j", Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false),
                    Animal.Type.FISH,
                    new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 100, 12, false),
                    Animal.Type.BIRD,
                    new Animal("h", Animal.Type.BIRD, Animal.Sex.M, 10, 8, 5, false)
                )

            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getTypeMaxWeightTest(List<Animal> animals, Map<Animal.Type, Animal> excepted)
        throws NullAnimalListException, NullAnimalException {
        assertThat(getTypeMaxWeightAnimal(animals)).isEqualTo(excepted);
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

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void getTypeMaxWeightExceptionTest(List<Animal> animals, Class<Exception> excepted) {
        assertThrows(excepted, () -> getTypeMaxWeightAnimal(animals));
    }

}
