package edu.hw8.Task3;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordGeneratorTest {

    private final PasswordGenerator passwordGeneratorWithEmptyMap = new PasswordGenerator(Map.of());

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                Map.of(
                    "2510c39011c5be704182423e3a695e91",
                    "v.v.belov",
                    "e1c80488853d86ab9d6decfe30d8930f",
                    "a.s.ivanov",
                    "e369853df766fa44e1ed0ff613f563bd",
                    "k.p.maslov",
                    "3100efe02018aa089541639c7c792800",
                    "a.v.petrov",
                    "06eb61b839a0cefee4967c67ccb099dc",
                    "a.v.krasnov",
                    "d20caec3b48a1eef164cb4ca81ba2587",
                    "a.l.krasnov",
                    "879aef8476823bc7dcc4a0f24eae1623",
                    "a.s.ivanova",
                    "e4b71c425a3eb7826750bfa545d49f70",
                    "a.v.petrova"
                ),
                Map.of(
                    "310",
                    "a.v.krasnov",
                    "34",
                    "k.p.maslov",
                    "L",
                    "a.l.krasnov",
                    "LR9S",
                    "a.s.ivanova",
                    "g2",
                    "a.s.ivanov",
                    "h",
                    "v.v.belov",
                    "ht6",
                    "a.v.petrov",
                    "qWeR",
                    "a.v.petrova"
                )
            ),
            Arguments.of(Map.of(
                "2510c39011c5be704182423e3a695e91",
                "v.v.belov",
                "e1c80488853d86ab9d6decfe30d8930f",
                "a.s.ivanov",
                "e369853df766fa44e1ed0ff613f563bd",
                "k.p.maslov"
            ), Map.of("34", "k.p.maslov", "g2", "a.s.ivanov", "h", "v.v.belov")),
            Arguments.of(Map.of(
                "2510c39011c5be704182423e3a695e91",
                "v.v.belov"
            ), Map.of("h", "v.v.belov")),
            Arguments.of(Map.of(), Map.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getPasswordMapWith4ThreadTest(Map<String, String> passwordMap, Map<String, String> excepted) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(passwordMap);
        assertThat(passwordGenerator.getPasswordMap(4)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getPasswordMapWithSingleThreadTest(Map<String, String> passwordMap, Map<String, String> excepted) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(passwordMap);
        assertThat(passwordGenerator.getPasswordMap(1)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"qwerty123, 3fc0a7acf087f549ac2b266baf94b8b1", "password1239,791c81cf35c5dd21eab13aafce594ed1",
        "qwertymew, 9fa05e3c5601f6e04def293898c63be7", "' ', 7215ee9c7d9dc229d2921a40e899ec5f"})
    void generateMD5HashTest(String password, String excepted) throws NoSuchAlgorithmException {
        assertThat(passwordGeneratorWithEmptyMap.generateMD5Hash(password)).isEqualTo(excepted);
    }

}
