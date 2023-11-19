package edu.project3.args;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class FilePatternFilter {

    private FilePatternFilter() {
    }

    private static DirectoryStream.Filter<Path> regexContains(String pattern) {
        return (entry -> pattern != null && Pattern.compile(pattern).matcher(entry.toString()).find());
    }

    private static DirectoryStream<Path> newDirectoryStream(Path path, String regex) throws IOException {
        return Files.newDirectoryStream(path, regexContains(regex));
    }

    public static List<Path> getPathsContainsRegex(String path) {
        int index = path.lastIndexOf("/");
        Path parentPath;
        String regex;
        if (index == -1) {
            parentPath = Path.of("");
            regex = path;
        } else {
            parentPath = Path.of(path.substring(0, index));
            regex = "*" + path.substring(index);
        }
        if (Files.isDirectory(parentPath)) {
            try (DirectoryStream<Path> newDirectoryStream = newDirectoryStream(parentPath, regex)) {
                Iterator<Path> pathIterator = newDirectoryStream.iterator();
                List<Path> paths = new ArrayList<>();
                while (pathIterator.hasNext()) {
                    paths.add(pathIterator.next());
                }
                return paths;
            } catch (IOException e) {
                throw new IllegalArgumentException(
                    "Неверный формат пути к log файлу. Ожидается локальный шаблон или URL.");
            }
        }
        return List.of();
    }
}
