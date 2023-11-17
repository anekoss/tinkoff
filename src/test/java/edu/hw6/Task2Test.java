package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw6.Task2.Task2.cloneFile;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @AfterAll
    static void removeAllTestFile() {
        provideDataForTest().forEach(arguments -> {
            try {
                Files.deleteIfExists((Path) arguments.get()[0]);
                Files.deleteIfExists((Path) arguments.get()[1]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                Path.of("Tinkoff Bank Biggest Secret.txt"),
                Path.of("Tinkoff Bank Biggest Secret — копия.txt")
            ),
            Arguments.of(
                Path.of("Tinkoff Bank Biggest Secret — копия.txt"),
                Path.of("Tinkoff Bank Biggest Secret — копия — копия.txt")
            ),
            Arguments.of(
                Path.of("Tinkoff Bank Biggest Secret — копия.txt"),
                Path.of("Tinkoff Bank Biggest Secret — копия — копия (1).txt")
            ),
            Arguments.of(
                Path.of("Tinkoff Bank Biggest Secret — копия.txt"),
                Path.of("Tinkoff Bank Biggest Secret — копия — копия (2).txt")
            ),
            Arguments.of(
                Path.of("Tinkoff Bank Biggest Secret — копия(1).txt"),
                Path.of("Tinkoff Bank Biggest Secret — копия(1) — копия.txt")
            ),
            Arguments.of(
                Path.of("Tinkoff Bank Biggest Secret — копия(1).txt"),
                Path.of("Tinkoff Bank Biggest Secret — копия(1) — копия (1).txt")
            ),
            Arguments.of(
                Path.of("Tinkoff Bank Biggest Secret.txt"),
                Path.of("Tinkoff Bank Biggest Secret — копия (1).txt")
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void cloneFileTest(Path path, Path excepted) throws IOException {
        assertThat(cloneFile(path)).isEqualTo(excepted);
    }
}
