package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task18.getMaxWeightFish;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task18Test {
    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    List.of(
                        new Animal("aa ff ff", Animal.Type.CAT, Animal.Sex.M, 10, 130, 100, true),
                        new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                    ), List.of(
                        new Animal("aa ff ff", Animal.Type.FISH, Animal.Sex.M, 10, 130, 1001, true),
                        new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                    ),
                    List.of(
                        new Animal("aa ff ff", Animal.Type.FISH, Animal.Sex.M, 10, 130, 1000, true),
                        new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                    )
                ), new Animal("aa ff ff", Animal.Type.FISH, Animal.Sex.M, 10, 130, 1001, true)
            ),
            Arguments.of(
                List.of(
                    List.of(
                        new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 12, 10, 1, true),
                        new Animal("b  a  b ", Animal.Type.DOG, Animal.Sex.F, 8, 15, 1, false),
                        new Animal("d fff", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                        new Animal("e dddf sss", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, 57, true),
                        new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 1000, 120, false),
                        new Animal("c s s s", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 4, true),
                        new Animal("g ss ss a A", Animal.Type.FISH, Animal.Sex.F, 4, 300, 2, true),
                        new Animal("hHHH rr r", Animal.Type.SPIDER, Animal.Sex.M, 10, 8, 5, false),
                        new Animal("i", Animal.Type.SPIDER, Animal.Sex.F, 15, 54, 2344, true),
                        new Animal("j", Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false)
                    ), List.of(
                        new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 12, 10, 1, true),
                        new Animal("b  a  b ", Animal.Type.DOG, Animal.Sex.F, 8, 15, 1, false),
                        new Animal("d fff", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                        new Animal("e dddf sss", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, 57, true),
                        new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1210, false),
                        new Animal("c s s s", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 4, true),
                        new Animal("g ss ss a A", Animal.Type.FISH, Animal.Sex.F, 4, 3, 2, true),
                        new Animal("hHHH rr r", Animal.Type.SPIDER, Animal.Sex.M, 10, 8, 5, false),
                        new Animal("i", Animal.Type.SPIDER, Animal.Sex.F, 15, 54, 2344, true),
                        new Animal("j", Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false)
                    )
                ), new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1210, false))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getMaxWeightFishTest(List<List<Animal>> animalLists, Animal excepted)
        throws NullAnimalListException, NullAnimalException, NotFoundAnimalException {
        assertThat(getMaxWeightFish(animalLists)).isEqualTo(excepted);
    }

    public static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of(List.of(List.of(), List.of(), List.of()), NotFoundAnimalException.class),
            Arguments.of(List.of(
                List.of(
                    new Animal("aa ff ff", Animal.Type.CAT, Animal.Sex.M, 10, 130, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                ), List.of(
                    new Animal("aa ff ff", Animal.Type.BIRD, Animal.Sex.M, 10, 130, 1001, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                )), NotFoundAnimalException.class),
            Arguments.of(null, NullAnimalListException.class),
            Arguments.of(Arrays.asList(null, null), NullAnimalListException.class),
            Arguments.of(
                Arrays.asList(
                    Stream.generate(() -> null).limit(1).toList(),
                    Arrays.asList(null, null),
                    List.of(new Animal("a", Animal.Type.DOG, Animal.Sex.M, 12, 10, 1, true))
                ),
                NullAnimalException.class
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void getMaxWeightFishTestExceptionTest(List<List<Animal>> animalLists, Class exceptedException) {
        assertThrows(exceptedException, () -> getMaxWeightFish(animalLists));
    }

}
