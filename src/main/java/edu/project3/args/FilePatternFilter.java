package edu.project3.args;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePatternFilter {

    private FilePatternFilter() {
    }

    public static List<Path> getPathsContainsRegex(String localPattern) {
        List<Path> matchingFiles = new ArrayList<>();
        int index = localPattern.lastIndexOf("/", localPattern.indexOf('*'));
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + localPattern);
        if (index == -1) {
            return List.of();
        }
        Path start;
        if (index == 0) {
            start = Paths.get("");
        } else {
            start = Paths.get(localPattern.substring(0, index));
        }
        try {
            Files.walk(start, FileVisitOption.FOLLOW_LINKS)
                .filter(Files::isRegularFile)
                .filter(matcher::matches)
                .forEach(matchingFiles::add);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                "Неверный формат пути к log файлу. Ожидается локальный шаблон или URL.",
                e
            );
        }

        return matchingFiles;
    }
}
