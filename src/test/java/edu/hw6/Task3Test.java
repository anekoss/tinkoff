package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
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
                Path.of("..\\tinkoff\\.editorconfig"),
                Path.of("..\\tinkoff\\.gitattributes"),
                Path.of("..\\tinkoff\\.gitignore"),
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\mvnw"),
                Path.of("..\\tinkoff\\mvnw.cmd"),
                Path.of("..\\tinkoff\\pom.xml"),
                Path.of("..\\tinkoff\\README.md")
            )),
            Arguments.of(writeable, List.of(
                Path.of("..\\tinkoff\\.editorconfig"),
                Path.of("..\\tinkoff\\.git"),
                Path.of("..\\tinkoff\\.gitattributes"),
                Path.of("..\\tinkoff\\.github"),
                Path.of("..\\tinkoff\\.gitignore"),
                Path.of("..\\tinkoff\\.idea"),
                Path.of("..\\tinkoff\\.mvn"),
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\mvnw"),
                Path.of("..\\tinkoff\\mvnw.cmd"),
                Path.of("..\\tinkoff\\pom.xml"),
                Path.of("..\\tinkoff\\README.md"),
                Path.of("..\\tinkoff\\src")
            )),
            Arguments.of(readable, List.of(
                Path.of("..\\tinkoff\\.editorconfig"),
                Path.of("..\\tinkoff\\.git"),
                Path.of("..\\tinkoff\\.gitattributes"),
                Path.of("..\\tinkoff\\.github"),
                Path.of("..\\tinkoff\\.gitignore"),
                Path.of("..\\tinkoff\\.idea"),
                Path.of("..\\tinkoff\\.mvn"),
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\mvnw"),
                Path.of("..\\tinkoff\\mvnw.cmd"),
                Path.of("..\\tinkoff\\pom.xml"),
                Path.of("..\\tinkoff\\README.md"),
                Path.of("..\\tinkoff\\src")
            )),
            Arguments.of(sizeLargerThan(200), List.of(
                Path.of("..\\tinkoff\\.editorconfig"),
                Path.of("..\\tinkoff\\.git"),
                Path.of("..\\tinkoff\\.gitignore"),
                Path.of("..\\tinkoff\\.idea"),
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\mvnw"),
                Path.of("..\\tinkoff\\mvnw.cmd"),
                Path.of("..\\tinkoff\\pom.xml"),
                Path.of("..\\tinkoff\\README.md")
            )),
            Arguments.of(sizeLessThan(1000), List.of(
                Path.of("..\\tinkoff\\.gitattributes"),
                Path.of("..\\tinkoff\\.github"),
                Path.of("..\\tinkoff\\.gitignore"),
                Path.of("..\\tinkoff\\.mvn"),
                Path.of("..\\tinkoff\\README.md"),
                Path.of("..\\tinkoff\\src")
            )),
            Arguments.of(sizeEquals(242), List.of(
                Path.of("..\\tinkoff\\README.md")
            )),
            Arguments.of(globMatches(".xml"), List.of(
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\pom.xml")
            )),
            Arguments.of(regexContains("^..[^.]*$"), List.of(
                Path.of("..\\tinkoff\\mvnw"),
                Path.of("..\\tinkoff\\src")
            )),
            Arguments.of(magicNumber(new byte[] {60, 63, 120, 109, 108, 32, 118, 101, 114, 115}), List.of(
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\pom.xml")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForFilterTest")
    void filterTest(AbstractFilter filter, List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Path.of("..\\tinkoff"), filter)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted);

        }
    }

    static Stream<Arguments> provideDataForGlobDirectoryStreamTest() {
        return Stream.of(
            Arguments.of(".xml", List.of(
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\pom.xml")
            )),
            Arguments.of(".git", List.of(
                Path.of("..\\tinkoff\\.git")
            )),
            Arguments.of(".idea", List.of(
                Path.of("..\\tinkoff\\.idea"))
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForGlobDirectoryStreamTest")
    void globTest(String glob, List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Paths.get("..\\tinkoff"), glob)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted);
        }
    }

    static Stream<Arguments> provideDataForDirectoryStreamTest() {
        return Stream.of(
            Arguments.of(List.of(
                Path.of("..\\tinkoff\\.editorconfig"),
                Path.of("..\\tinkoff\\.git"),
                Path.of("..\\tinkoff\\.gitattributes"),
                Path.of("..\\tinkoff\\.github"),
                Path.of("..\\tinkoff\\.gitignore"),
                Path.of("..\\tinkoff\\.idea"),
                Path.of("..\\tinkoff\\.mvn"),
                Path.of("..\\tinkoff\\checkstyle.xml"),
                Path.of("..\\tinkoff\\mvnw"),
                Path.of("..\\tinkoff\\mvnw.cmd"),
                Path.of("..\\tinkoff\\pom.xml"),
                Path.of("..\\tinkoff\\README.md"),
                Path.of("..\\tinkoff\\src")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForDirectoryStreamTest")
    void newDirectoryStreamTest(List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Paths.get("..\\tinkoff"))) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted);
            try (Stream<Path> exceptedPathStream = Files.list(Path.of("..\\tinkoff"))) {

                assertThat(pathList).isEqualTo(exceptedPathStream.toList());
            }
        }
    }

    static List<Path> getStreamAsList(DirectoryStream<Path> stream) {
        Iterator<Path> iterator = stream.iterator();
        List<Path> pathList = new ArrayList<>();
        while (iterator.hasNext()) {
            pathList.add(iterator.next());
        }
        return pathList;
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
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Path.of("..\\tinkoff"), filter)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted);
        }
    }

    @ParameterizedTest
    @MethodSource("orFilterTest")
    void orTest(AbstractFilter filter, List<Path> excepted) throws IOException {
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Path.of("..\\tinkoff"), filter)) {
            List<Path> pathList = getStreamAsList(directoryStream);
            assertThat(pathList).isEqualTo(excepted);
        }
    }
}
