package edu.hw6;

import edu.hw6.Task4.Task4;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of("1.txt", "fjfjfjfjjf", "1 — копия.txt"),
            Arguments.of("233.txt", "ffjfjf", "233 — копия.txt"),
            Arguments.of("233ddkd.txt", "ffjddkdfjf\nfdjddjdj", "233ddkd — копия.txt"),
            Arguments.of(
                "task4.txt",
                "Programming is learned by writing programs. ― Brian Kernighan",
                "task4 — копия.txt"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getFileUsingCompositionOutputStreamTest(String filePath, String string, String copyFilePath)
        throws IOException {
        assertThat(new Task4().getFileUsingCompositionOutputStream(filePath, string)).exists()
            .isEqualTo(Path.of(filePath));
        assertThat(String.join("\n", Files.readAllLines(Path.of(filePath)))).isEqualTo(string);
        assertThat(new Task4().getFileUsingCompositionOutputStream(filePath, string)).exists()
            .isEqualTo(Path.of(copyFilePath));
        Files.delete(Paths.get(filePath));
        Files.delete(Paths.get(copyFilePath));
    }

}
