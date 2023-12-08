package edu.hw9.Task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class DirectorySearch extends RecursiveTask<Set<Path>> {
    private final Path rootPath;
    private final int count;

    public DirectorySearch(Path rootPath, int count) {
        if (!Files.exists(rootPath)) {
            throw new IllegalArgumentException();
        }
        this.rootPath = rootPath;
        this.count = count;
    }

    @SneakyThrows @Override
    protected Set<Path> compute() {
        Set<Path> results = new HashSet<>();
        List<DirectorySearch> directorySearchers;
        try (Stream<Path> paths = Files.list(rootPath)) {
            List<Path> childPaths = paths.toList();
            if (childPaths.stream().filter(Files::isRegularFile).count() > count) {
                results.add(rootPath);
            }
            directorySearchers =
                childPaths.stream().filter(Files::isDirectory).map(path -> new DirectorySearch(path, count)).toList();
            if (!directorySearchers.isEmpty()) {
                directorySearchers.forEach(DirectorySearch::fork);
                directorySearchers.stream().map(DirectorySearch::join).forEach(results::addAll);
            } else {
                return results;
            }
        }
        return results;
    }
}
