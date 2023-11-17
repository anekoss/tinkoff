package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw6.Task3.DirectoryFilter.globMatches;
import static edu.hw6.Task3.DirectoryFilter.magicNumber;
import static edu.hw6.Task3.DirectoryFilter.readable;
import static edu.hw6.Task3.DirectoryFilter.regexContains;
import static edu.hw6.Task3.DirectoryFilter.regularFile;
import static edu.hw6.Task3.DirectoryFilter.sizeEquals;
import static edu.hw6.Task3.DirectoryFilter.sizeLargerThan;
import static edu.hw6.Task3.DirectoryFilter.sizeLessThan;
import static edu.hw6.Task3.DirectoryFilter.writeable;
import static edu.hw6.Task3.Task3.newDirectoryStream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    static Stream<Arguments> provideDataForFilterTest() {
        return Stream.of(
            Arguments.of(regularFile, List.of(
                Path.of(".editorconfig"),
                Path.of(".gitattributes"),
                Path.of(".gitignore"),
                Path.of("checkstyle.xml"),
                Path.of("mvnw"),
                Path.of("mvnw.cmd"),
                Path.of("pom.xml"),
                Path.of("README.md")
            )),
            Arguments.of(writeable, List.of(
                Path.of(".editorconfig"),
                Path.of(".git"),
                Path.of(".gitattributes"),
                Path.of(".gitignore"),
                Path.of(".mvn"),
                Path.of("checkstyle.xml"),
                Path.of("mvnw"),
                Path.of("mvnw.cmd"),
                Path.of("pom.xml"),
                Path.of("README.md"),
                Path.of("src"),
                Path.of("target")
            )),
            Arguments.of(readable, List.of(
                Path.of(".editorconfig"),
                Path.of(".git"),
                Path.of(".gitattributes"),
                Path.of(".gitignore"),
                Path.of(".mvn"),
                Path.of("checkstyle.xml"),
                Path.of("mvnw"),
                Path.of("mvnw.cmd"),
                Path.of("pom.xml"),
                Path.of("README.md"),
                Path.of("src"),
                Path.of("target")
            )),
            Arguments.of(sizeLargerThan(200), List.of(
                Path.of(".editorconfig"),
                Path.of(".git"),
                Path.of(".gitignore"),
                Path.of("checkstyle.xml"),
                Path.of("mvnw"),
                Path.of("mvnw.cmd"),
                Path.of("pom.xml"),
                Path.of("README.md"),
                Path.of("target")
            )),
            Arguments.of(sizeLessThan(1000), List.of(
                Path.of(".gitattributes"),
                Path.of(".gitignore"),
                Path.of(".mvn"),
                Path.of("README.md"),
                Path.of("src")
            )),
            Arguments.of(sizeEquals(242), List.of(
                Path.of("README.md")
            )),
            Arguments.of(globMatches(".xml"), List.of(
                Path.of("checkstyle.xml"),
                Path.of("pom.xml")
            )),
            Arguments.of(regexContains("^[^.]*$"), List.of(
                Path.of("mvnw"),
                Path.of("src"),
                Path.of("target")
            )),
            Arguments.of(magicNumber(new byte[] {60, 63, 120, 109, 108, 32, 118, 101, 114, 115}), List.of(
                Path.of("checkstyle.xml"),
                Path.of("pom.xml")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForFilterTest")
    void filterTest(AbstractFilter filter, List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Path.of(""), filter)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted.stream().sorted().toList());

        }
    }

    static Stream<Arguments> provideDataForGlobDirectoryStreamTest() {
        return Stream.of(
            Arguments.of(".xml", List.of(
                Path.of("checkstyle.xml"),
                Path.of("pom.xml")
            )),
            Arguments.of(".git", List.of(
                Path.of(".git")
            )),
            Arguments.of(".idea", List.of(
                Path.of(".idea"))
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForGlobDirectoryStreamTest")
    void globTest(String glob, List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Paths.get(""), glob)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted.stream().sorted().toList());
        }
    }

    static Stream<Arguments> provideDataForDirectoryStreamTest() {
        return Stream.of(
            Arguments.of(List.of(
                Path.of(".editorconfig"),
                Path.of(".git"),
                Path.of(".gitattributes"),
                Path.of(".gitignore"),
                Path.of(".mvn"),
                Path.of("checkstyle.xml"),
                Path.of("mvnw"),
                Path.of("mvnw.cmd"),
                Path.of("pom.xml"),
                Path.of("README.md"),
                Path.of("src"),
                Path.of("target")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForDirectoryStreamTest")
    void newDirectoryStreamTest(List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Paths.get(""))) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted.stream().sorted().toList());
            try (Stream<Path> exceptedPathStream = Files.list(Path.of(""))) {

                assertThat(pathList).isEqualTo(exceptedPathStream.toList());
            }
        }
    }

    static List<Path> getStreamAsList(DirectoryStream<Path> stream) {
        List<Path> paths = new ArrayList<>();
        for (Path path : stream) {
            paths.add(path);
        }
        return paths.stream().sorted().toList();
    }

    static Stream<Arguments> andFilterTest() {
        List<AbstractFilter> filters =
            provideDataForFilterTest().map(arguments -> (AbstractFilter) arguments.get()[0]).toList();
        List<List<Path>> pathList =
            provideDataForFilterTest().map(arguments -> (List<Path>) arguments.get()[1]).toList();
        List<Arguments> arguments = new ArrayList<>();
        for (int i = 0; i < filters.size() - 1; i++) {
            AbstractFilter filter = filters.get(i);
            List<Path> paths = pathList.get(i);
            for (int j = i; j < filters.size(); j++) {
                filter = filter.and(filters.get(j));
                paths = paths.stream().filter(pathList.get(j)::contains).toList();
                arguments.add(Arguments.of(filter, paths));
            }
        }
        return arguments.stream();
    }

    static Stream<Arguments> orFilterTest() {
        List<AbstractFilter> filters =
            provideDataForFilterTest().map(arguments -> (AbstractFilter) arguments.get()[0]).toList();
        List<List<Path>> pathList =
            provideDataForFilterTest().map(arguments -> (List<Path>) arguments.get()[1]).toList();
        List<Arguments> arguments = new ArrayList<>();
        for (int i = 0; i < filters.size() - 1; i++) {
            AbstractFilter filter = filters.get(i);
            List<Path> paths = pathList.get(i);
            for (int j = i; j < filters.size(); j++) {
                filter = filter.or(filters.get(j));
                List<Path> excepted = new ArrayList<>(paths);
                for (Path path : pathList.get(j)) {
                    if (!excepted.contains(path)) {
                        excepted.add(path);
                    }
                }
                paths = excepted;
                arguments.add(Arguments.of(filter, paths.stream().sorted().toList()));
            }
        }
        return arguments.stream();
    }

    @ParameterizedTest
    @MethodSource("andFilterTest")
    void andTest(AbstractFilter filter, List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Path.of(""), filter)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted.stream().sorted().toList());
        }
    }

    @ParameterizedTest
    @MethodSource("orFilterTest")
    void orTest(AbstractFilter filter, List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Path.of(""), filter)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted.stream().sorted().toList());
        }
    }
}
