package edu.hw4;

import edu.hw4.Animal.Animal;
import edu.hw4.Animal.NullAnimalException;
import edu.hw4.Animal.NullAnimalListException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw4.Task1_18.Task1To18.hasSpidersBitesMoreDogs;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task17Test {
    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(List.of(), false),
            Arguments.of(
                List.of(
                    new Animal("aa ff ff", Animal.Type.CAT, Animal.Sex.M, 10, 130, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                ), false)
            , Arguments.of(
                List.of(
                    new Animal("aa ff ff", Animal.Type.SPIDER, Animal.Sex.M, 10, 130, 100, true),
                    new Animal("b", Animal.Type.FISH, Animal.Sex.M, 12, 13, 100, true)
                ), false
            ),
            Arguments.of(
                List.of(
                    new Animal(null, Animal.Type.SPIDER, Animal.Sex.M, 12, 13, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, false)
                ), false
            ),
            Arguments.of(
                List.of(
                    new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 12, 10, 1, true),
                    new Animal("b  a  b ", Animal.Type.DOG, Animal.Sex.F, 8, 15, 1, false),
                    new Animal("d fff", Animal.Type.CAT, Animal.Sex.F, 5, 25, 100, false),
                    new Animal("e dddf sss", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, 57, true),
                    new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 100, 12, false),
                    new Animal("c s s s", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 4, true),
                    new Animal("g ss ss a A", Animal.Type.FISH, Animal.Sex.F, 4, 3, 2, true),
                    new Animal("hHHH rr r", Animal.Type.SPIDER, Animal.Sex.M, 10, 8, 5, false),
                    new Animal("i", Animal.Type.SPIDER, Animal.Sex.F, 15, 54, 2344, true),
                    new Animal("j", Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false)
                ), true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void hasSpidersBitesMoreDogsTest(List<Animal> animals, boolean excepted)
        throws NullAnimalListException, NullAnimalException {
        assertThat(hasSpidersBitesMoreDogs(animals)).isEqualTo(excepted);
    }

    public static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of(null, NullAnimalListException.class),
            Arguments.of(Arrays.asList(null, null), NullAnimalException.class),
            Arguments.of(
                Arrays.asList(null, new Animal("a", Animal.Type.DOG, Animal.Sex.M, 12, 10, 1, true)),
                NullAnimalException.class
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void hasSpidersBitesMoreDogsExceptionTest(List<Animal> animals, Class<Exception> exceptedException) {
        assertThrows(exceptedException, () -> hasSpidersBitesMoreDogs(animals));
    }

}
