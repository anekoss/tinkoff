package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task2.sortAnimalsByWeight;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of(), List.of()),
            Arguments.of(Arrays.asList(null, null), Arrays.asList(null, null)),
            Arguments.of(
                Arrays.asList(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true), null),
                Arrays.asList(null, new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true))
            ),
            Arguments.of(
                Arrays.asList(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true)
                ),
                Arrays.asList(
                    new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", Animal.Type.CAT, Animal.Sex.M, 12, 13, 100, true)
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
            ), List.of(

                new Animal("i", Animal.Type.DOG, Animal.Sex.F, 15, 54, 2344, true),
                new Animal("j", Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false),
                new Animal("d", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                new Animal("e", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, 57, true),
                new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 100, 12, false),
                new Animal("h", Animal.Type.BIRD, Animal.Sex.M, 10, 8, 5, false),
                new Animal("c", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 4, true),
                new Animal("g", Animal.Type.FISH, Animal.Sex.F, 4, 3, 2, true),
                new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true),
                new Animal("b", Animal.Type.DOG, Animal.Sex.F, 8, 15, 1, false)
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void sortAnimalsByWeightTest(List<Animal> animals, List<Animal> sortedAnimals) {
        int k = animals.size();
        for (int i = 0; i <= k; i++) {
            assertThat(sortAnimalsByWeight(animals, i)).isEqualTo(sortedAnimals.subList(0, i));
        }
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void sortAnimalsByWeightIncorrectKTest(List<Animal> animals, List<Animal> sortedAnimals) {
        int k = animals.size();
        assertThat(sortAnimalsByWeight(animals, -1)).isEqualTo(List.of());
        assertThat(sortAnimalsByWeight(animals, k + 1)).isEqualTo(sortedAnimals);
    }

}
