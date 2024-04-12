package edu.hw9.Task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class FileSearchByExtension extends RecursiveTask<Set<Path>> {
    private final Path rootPath;
    private final String extension;

    public FileSearchByExtension(@NotNull Path rootPath, @NotNull String extension) {
        if (!Files.exists(rootPath)) {
            throw new IllegalArgumentException();
        }
        this.rootPath = rootPath;
        this.extension = extension;
    }

    @SneakyThrows @Override
    protected Set<Path> compute() {
        Set<Path> results = new HashSet<>();
        List<FileSearchByExtension> fileSearchByExtensions;
        try (Stream<Path> paths = Files.list(rootPath)) {
            List<Path> childPaths = paths.toList();
            childPaths.stream().filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().lastIndexOf(".") != -1 && path.getFileName().toString()
                    .substring(path.getFileName().toString().lastIndexOf(".") + 1).equals(extension))
                .forEach(results::add);
            fileSearchByExtensions =
                childPaths.stream().filter(Files::isDirectory).map(path -> new FileSearchByExtension(path, extension))
                    .toList();
            if (!fileSearchByExtensions.isEmpty()) {
                fileSearchByExtensions.forEach(FileSearchByExtension::fork);
                fileSearchByExtensions.stream().map(FileSearchByExtension::join).forEach(results::addAll);
            } else {
                return results;
            }
        }
        return results;
    }
}
