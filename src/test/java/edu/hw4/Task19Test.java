package edu.hw4;

import edu.hw4.Animal.Animal;
import edu.hw4.Animal.NullAnimalException;
import edu.hw4.Animal.NullAnimalListException;
import edu.hw4.Task19.Task19;
import edu.hw4.Task19.TypeError;
import edu.hw4.Task19.ValidationError;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task19Test {

    public static Stream<Arguments> provideDataForTest() {
        HashMap<String, Set<ValidationError>> exceptedMap1 = new HashMap<>(Map.of(
            "b",
            Set.of(
                new ValidationError("age", TypeError.NEGATIVE_OR_ZERO),
                new ValidationError("height", TypeError.NEGATIVE_OR_ZERO),
                new ValidationError("sex", TypeError.NULL)
            )
        ));
        exceptedMap1.put(
            null,
            Set.of(new ValidationError("name", TypeError.NULL), new ValidationError("type", TypeError.NULL))
        );

        HashMap<String, Set<ValidationError>> exceptedMap2 = new HashMap<>(Map.of(
            "a a",
            Set.of(new ValidationError("name", TypeError.NO_UNIQUE)),
            "b  a  b",
            Set.of(
                new ValidationError("age", TypeError.NEGATIVE_OR_ZERO)),
            "d fff",
            Set.of(new ValidationError("height", TypeError.NEGATIVE_OR_ZERO)),
            "e dddf sss",
            Set.of(new ValidationError("weight", TypeError.NEGATIVE_OR_ZERO)),
            "g ss ss a A",
            Set.of(new ValidationError("type", TypeError.NULL)),
            "hHHH rr r",
            Set.of(new ValidationError("sex", TypeError.NULL))
        )
        );
        exceptedMap2.put(
            null,
            Set.of(new ValidationError("name", TypeError.NULL))
        );

        return Stream.of(
            Arguments.of(List.of(), Map.of()),
            Arguments.of(
                List.of(
                    new Animal("aa ff ff", Animal.Type.CAT, Animal.Sex.M, 10, 130, 100, true),
                    new Animal("b", Animal.Type.DOG, Animal.Sex.M, 12, 13, 100, true)
                ), Map.of())
            , Arguments.of(
                List.of(
                    new Animal(null, null, Animal.Sex.M, 10, 130, 100, true),
                    new Animal("b", Animal.Type.FISH, null, -12, -13, 100, true)
                ),
                exceptedMap1
            ),
            Arguments.of(
                List.of(
                    new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 12, 10, 1, true),
                    new Animal("b  a  b", Animal.Type.DOG, Animal.Sex.F, -8, 15, 1, false),
                    new Animal("d fff", Animal.Type.CAT, Animal.Sex.F, 5, -25, 100, false),
                    new Animal("e dddf sss", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, -57, true),
                    new Animal("f", Animal.Type.FISH, Animal.Sex.M, 1, 100, 12, false),
                    new Animal("c s s s", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 4, true),
                    new Animal("g ss ss a A", null, Animal.Sex.F, 4, 3, 2, true),
                    new Animal("hHHH rr r", Animal.Type.SPIDER, null, 10, 8, 5, false),
                    new Animal("a a", Animal.Type.SPIDER, Animal.Sex.F, 15, 54, 2344, true),
                    new Animal(null, Animal.Type.SPIDER, Animal.Sex.M, 6, 4, 234, false)
                ),
                exceptedMap2
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getFieldErrorTest(List<Animal> animals, Map<String, Set<ValidationError>> excepted)
        throws NullAnimalListException, NullAnimalException {
        assertThat(new Task19().getFieldErrorsInAllAnimals(animals)).isEqualTo(excepted);
    }

    public static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of(null, NullAnimalListException.class),
            Arguments.of(Arrays.asList(null, null), NullAnimalException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void getFieldErrorExceptionTest(List<Animal> animals, Class<Exception> exceptedException) {
        assertThrows(exceptedException, () -> new Task19().getFieldErrorsInAllAnimals(animals));
    }

}

