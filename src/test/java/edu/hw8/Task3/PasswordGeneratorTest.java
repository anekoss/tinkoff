package edu.hw8.Task3;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordGeneratorTest {

    private Map<String, String> passwordMap = Map.of(
        "9596280d73f4c278a2878e7d6383b5eb",
        "v.v.belov",
        "1954899c048f2360a3c5384282be2aed",
        "a.s.ivanov",
        "1944ecf4e6b566a11f75f38233ed0aee",
        "k.p.maslov",
        "7c5aba41f53293b712fd86d08ed5b36e",
        "a.v.petrov",
        "39d681ea803dd4c1381087c05d824661",
        "a.v.krasnov",
        "8f829072eeade4e0246e9070fc0855a4",
        "a.l.krasnov",
        "4b673ef9d9869d2661a128b4d115f5fc",
        "a.s.ivanova",
        "992e4945aa72b95ee516306b7e50e525",
        "a.v.petrova"
    );
//
//    @ParameterizedTest
//    @CsvSource({"'', d41d8cd98f00b204e9800998ecf8427e", "They are deterministic, 23db6982caef9e9152f1a5b2589e6ca3",
//        "123456, e10adc3949ba59abbe56e057f20f883e", "qwerty, d8578edf8458ce06fbc5bb76a58c5ca4",
//        "password123, 482c811da5d5b4bc6d497ffa98491e38", "password, 5f4dcc3b5aa765d61d8327deb882cf99"})
//    void generateMD5HashTest(String password, String excepted) throws NoSuchAlgorithmException {
//        assertThat(new PasswordGenerator(new HashMap<>()).generateMD5Hash(password)).isEqualTo(excepted);
//    }

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(-1, Map.of()),
            Arguments.of(0, Map.of()),
            Arguments.of(1, Map.of()),
            Arguments.of(2, Map.of("j7", "a.v.petrova")),
            Arguments.of(3, Map.of("hel", "a.l.krasnov")),
            Arguments.of(4, Map.of()),
            Arguments.of(5, Map.of())
        );
    }

    @Test
    void getPasswordWithLengthK() throws NoSuchAlgorithmException {
        PasswordGenerator passwordGenerator = new PasswordGenerator(passwordMap);
        assertThat(passwordGenerator.getPasswordMap(4)).isEqualTo(null);
    }

}
