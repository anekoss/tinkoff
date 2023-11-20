package edu.project3;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project3.args.FilePatternFilter.getPathsContainsRegex;
import static org.assertj.core.api.Assertions.assertThat;

public class FilePatternFilterTest {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of("*/logs/lo*", List.of(Path.of("testLog/logs/logFile.txt"))),
            Arguments.of("*/logs/logFile.txt", List.of(Path.of("testLog/logs/logFile.txt"))),
            Arguments.of("*/*/logFile.txt", List.of(Path.of("testLog/logs/logFile.txt"))),
            Arguments.of("*/*/logFile.txtt", List.of()),
            Arguments.of(
                "*/lo*",
                List.of(Path.of("testLog/logFile.txt"), Path.of("testLog/logs(1).txt"), Path.of("testLog/logs.txt"))
            ),
            Arguments.of(
                "*/*.txt",
                List.of(Path.of("testLog/logFile.txt"), Path.of("testLog/logs(1).txt"), Path.of("testLog/logs.txt"))
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getPathsContainsRegexTest(String path, List<Path> exceptedPath) {
        assertThat(getPathsContainsRegex(path).stream().sorted().toList()).isEqualTo(exceptedPath.stream().sorted()
            .toList());
    }
}
