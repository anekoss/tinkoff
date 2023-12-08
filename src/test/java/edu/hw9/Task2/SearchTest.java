package edu.hw9.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest {
    private final static String DIRECTORY_SEARCH_PATH = "src/main/resources/hw9/root";

    @BeforeAll
    static void generateFileSystem() throws IOException {
        Files.createDirectory(Path.of(DIRECTORY_SEARCH_PATH));
        for (int i = 0; i < 10; i++) {
            Files.createDirectory(Path.of(DIRECTORY_SEARCH_PATH + "/child" + i));
            for (int j = 0; j < 889; j++) {
                Path path = Path.of(DIRECTORY_SEARCH_PATH + "/child" + i + "/file" + j + ".txt");
                Files.createFile(path);
                Files.writeString(path, "true");
            }
            for (int j = 889; j < 891; j++) {
                Path path = Path.of(DIRECTORY_SEARCH_PATH + "/child" + i + "/file" + j + ".md");
                Files.createFile(path);
                Files.writeString(path, "true");
            }
        }
        for (int i = 0; i < 5; i++) {
            Files.createDirectory(Path.of(DIRECTORY_SEARCH_PATH + "/child" + i + "/child" + i));
            for (int j = 0; j < 999; j++) {
                Path path = Path.of(DIRECTORY_SEARCH_PATH + "/child" + i + "/child" + i + "/file" + j + ".txt");
                Files.createFile(path);
                Files.writeString(path, "false");
            }
            for (int j = 999; j < 1005; j++) {
                Path path = Path.of(DIRECTORY_SEARCH_PATH + "/child" + i + "/child" + i + "/file" + j + ".md");
                Files.createFile(path);
                Files.writeString(path, "false");
            }
        }
        Path path = Path.of(DIRECTORY_SEARCH_PATH + "/file.doc");
        Files.createFile(path);
        Files.writeString(path, "is big doc test file");

    }

    @AfterAll
    public static void deleteFileSystem() throws IOException {
        Path rootDirectory = Path.of(DIRECTORY_SEARCH_PATH);
        Files.walk(rootDirectory)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(Set.of(
                Path.of("src/main/resources/hw9/root/child0/child0"),
                Path.of("src/main/resources/hw9/root/child1/child1"),
                Path.of("src/main/resources/hw9/root/child2/child2"),
                Path.of("src/main/resources/hw9/root/child3/child3"),
                Path.of("src/main/resources/hw9/root/child4/child4")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void directorySearchTest(Set<Path> excepted) {
        assertThat(new Search(new ForkJoinPool()).searchDirectoryByMoreFilesCount(
            Path.of(DIRECTORY_SEARCH_PATH),
            1000
        )).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"md, 50", "txt, 13885", "doc, 1"})
    void fileSearchByExtensionTest(String extension, int exceptedCount) {
        Set<Path> actual = new Search(new ForkJoinPool()).searchFileByExtension(
            Path.of(DIRECTORY_SEARCH_PATH),
            extension
        );
        assertThat(actual.size()).isEqualTo(exceptedCount);
    }

    @ParameterizedTest
    @CsvSource({"5, 5025", "4,8910", "0,0", "20,1"})
    void fileSearchBySizeTest(long fileSize, int exceptedCount) {
        Set<Path> actual = new Search(new ForkJoinPool()).searchFileBySize(
            Path.of(DIRECTORY_SEARCH_PATH),
            fileSize
        );

        assertThat(actual.size()).isEqualTo(exceptedCount);
    }

    @Test
    void fileSearchTest() {
        Search search = new Search(new ForkJoinPool());
        Path excepted = Path.of(
            DIRECTORY_SEARCH_PATH + "/file.doc");
        Path rootPath = Path.of(DIRECTORY_SEARCH_PATH);
        assertThat(search.searchFileBySize(rootPath, 20)).isEqualTo(Set.of(excepted));
        assertThat(search.searchFileByExtension(rootPath, "doc")).isEqualTo(Set.of(excepted));
    }
}
