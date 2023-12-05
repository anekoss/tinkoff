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
            Arguments.of("src/main/resources/project3/*/logs/lo*", List.of(Path.of("src/main/resources/project3/testLog/logs/logFile.txt"))),
            Arguments.of("src/main/resources/project3/*/logs/logFile.txt", List.of(Path.of("src/main/resources/project3/testLog/logs/logFile.txt"))),
            Arguments.of("src/main/resources/project3/*/*/logFile.txt", List.of(Path.of("src/main/resources/project3/testLog/logs/logFile.txt"))),
            Arguments.of("src/main/resources/project3/*/*/logFile.txtt", List.of()),
            Arguments.of(
                "src/main/resources/project3/*/lo*",
                List.of(Path.of("src/main/resources/project3/testLog/logFile.txt"), Path.of("src/main/resources/project3/testLog/logs(1).txt"), Path.of("src/main/resources/project3/testLog/logs.txt"))
            ),
            Arguments.of(
                "src/main/resources/project3/*/*.txt",
                List.of(Path.of("src/main/resources/project3/testLog/logFile.txt"), Path.of("src/main/resources/project3/testLog/logs(1).txt"), Path.of("src/main/resources/project3/testLog/logs.txt"))
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
