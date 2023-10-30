package edu.hw4;

import edu.hw4.Animal.NullAnimalException;
import edu.hw4.Animal.NullAnimalListException;
import edu.hw4.Task19.TypeError;
import edu.hw4.Task19.ValidationError;
import edu.hw4.Task20.Task20;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task20Test {

    public static Stream<Arguments> provideDataForTest() {
        HashMap<String, Set<ValidationError>> errorMap1 = new HashMap<>(Map.of(
            "b",
            Set.of(
                new ValidationError("age", TypeError.NEGATIVE_OR_ZERO),
                new ValidationError("height", TypeError.NEGATIVE_OR_ZERO),
                new ValidationError("sex", TypeError.NULL)
            )
        ));
        errorMap1.put(
            null,
            Set.of(new ValidationError("name", TypeError.NULL), new ValidationError("type", TypeError.NULL))
        );

        HashMap<String, Set<ValidationError>> errorMap2 = new HashMap<>(Map.of(
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
        errorMap2.put(
            null,
            Set.of(new ValidationError("name", TypeError.NULL))
        );

        HashMap<String, String> exceptedMap1 =
            new HashMap<>(Map.of("b", "field age : NEGATIVE_OR_ZERO,field height : NEGATIVE_OR_ZERO,field sex : NULL"));
        exceptedMap1.put(null, "field name : NULL,field type : NULL");
        HashMap<String, String> exceptedMap2 = new HashMap<>(Map.of(
            "a a",
            "field name : NO_UNIQUE",
            "hHHH rr r",
            "field sex : NULL",
            "g ss ss a A",
            "field type : NULL",
            "d fff",
            "field height : NEGATIVE_OR_ZERO",
            "e dddf sss",
            "field weight : NEGATIVE_OR_ZERO",
            "b  a  b", "field age : NEGATIVE_OR_ZERO"
        ));
        exceptedMap2.put(null, "field name : NULL");
        return Stream.of(
            Arguments.of(Map.of(), Map.of())
            , Arguments.of(
                errorMap1, exceptedMap1
            ),
            Arguments.of(
                errorMap2, exceptedMap2
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getStringErrorTest(Map<String, Set<ValidationError>> errorMap, Map<String, String> excepted) {
        assertThat(new Task20().getStringErrorAnimals(errorMap)).isEqualTo(excepted);
    }

    public static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of(null, NullAnimalListException.class),
            Arguments.of(Arrays.asList(null, null), NullAnimalException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void getStringErrorExceptionTest(Map<String, Set<ValidationError>> errorMap, Class<Exception> exceptedException) {
        assertThrows(exceptedException, () -> new Task20().getStringErrorAnimals(errorMap));
    }

}
