package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task5.getMaxSexAnimal;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(), "equals"
            ),
            Arguments.of(Arrays.asList(null, null), "equals"),
            Arguments.of(
                Arrays.asList(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true), null),
                Animal.Sex.M.name()
            ),
            Arguments.of(
                Arrays.asList(
                    new Animal("aa", Animal.Type.CAT, Animal.Sex.F, 12, 13, 100, true),
                    new Animal("bdjfjdjfj", Animal.Type.CAT, Animal.Sex.F, 12, 13, 100, true)
                ),
                Animal.Sex.F.name()
            ),
            Arguments.of(
                Arrays.asList(
                    new Animal("a", Animal.Type.CAT, null, 12, 13, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                ),
                Animal.Sex.M.name()
            ),
            Arguments.of(
                List.of(
                    new Animal("aaf", Animal.Type.CAT, Animal.Sex.M, 12, 10, 1, true),
                    new Animal("bfff", Animal.Type.DOG, Animal.Sex.F, 8, 15, 1, false),
                    new Animal("dsdsd", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                    new Animal("efff", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, 57, true),
                    new Animal("ff", Animal.Type.FISH, Animal.Sex.M, 1, 100, 12, false),
                    new Animal("coticdjjd", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 4, true),
                    new Animal("fffjjfg", Animal.Type.FISH, Animal.Sex.F, 4, 3, 2, true),
                    new Animal("hffff", Animal.Type.BIRD, Animal.Sex.M, 10, 8, 5, false),
                    new Animal("iffffffff", Animal.Type.DOG, Animal.Sex.F, 15, 54, 2344, true),
                    new Animal("fffffffffffffj", Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false)
                ),
                Animal.Sex.M.name()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void maxNameAnimalTest(List<Animal> animals, String excepted) {
        assertThat(getMaxSexAnimal(animals)).isEqualTo(excepted);
    }
}
