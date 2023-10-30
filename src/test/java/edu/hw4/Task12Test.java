package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task12.getCntWeightMoreHeightAnimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task12Test {
    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of(), 0),
            Arguments.of(
                Arrays.asList(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true)
                ),
                2
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
            ), 4)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getCntWeightMoreHeightTest(List<Animal> animals, Integer excepted)
        throws NullAnimalListException, NullAnimalException {
        int k = animals.size();
        for (int i = 0; i <= k; i++) {
            assertThat(getCntWeightMoreHeightAnimal(animals)).isEqualTo(excepted);
        }
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
    void getCntWeightMoreHeightExceptionTest(List<Animal> animals, Class exceptedException) {

        assertThrows(exceptedException, () -> getCntWeightMoreHeightAnimal(animals));
    }

}
