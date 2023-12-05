package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project3.report.CloneFile.cloneFile;
import static org.assertj.core.api.Assertions.assertThat;

public class CloneFileTest {

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
                Path.of("log.txt"),
                Path.of("log — копия.txt")
            ),
            Arguments.of(
                Path.of("log — копия.txt"),
                Path.of("log — копия — копия.txt")
            ),
            Arguments.of(
                Path.of("log — копия.txt"),
                Path.of("log — копия — копия (1).txt")
            ),
            Arguments.of(
                Path.of("log — копия.txt"),
                Path.of("log — копия — копия (2).txt")
            ),
            Arguments.of(
                Path.of("log — копия(1).txt"),
                Path.of("log — копия(1) — копия.txt")
            ),
            Arguments.of(
                Path.of("log — копия(1).txt"),
                Path.of("log — копия(1) — копия (1).txt")
            ),
            Arguments.of(
                Path.of("log.txt"),
                Path.of("log — копия (1).txt")
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void cloneFileTest(Path path, Path excepted) throws IOException {
        assertThat(cloneFile(path)).isEqualTo(excepted);
    }
}
