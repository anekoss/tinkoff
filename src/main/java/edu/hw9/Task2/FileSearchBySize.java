package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class FileSearchBySize extends RecursiveTask<Set<Path>> {
    private final Path rootPath;
    private final long fileSize;

    public FileSearchBySize(Path rootPath, long fileSize) {
        if (!Files.exists(rootPath)) {
            throw new IllegalArgumentException();
        }
        this.rootPath = rootPath;
        this.fileSize = fileSize;
    }

    @SneakyThrows @Override
    protected Set<Path> compute() {
        Set<Path> results = new HashSet<>();
        List<FileSearchBySize> fileSearchBySizes;
        try (Stream<Path> paths = Files.list(rootPath)) {
            List<Path> childPaths = paths.toList();
            childPaths.stream().filter(Files::isRegularFile).filter(path -> {
                try {
                    return Files.size(path) == fileSize;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).forEach(results::add);
            fileSearchBySizes =
                childPaths.stream().filter(Files::isDirectory).map(path -> new FileSearchBySize(path, fileSize))
                    .toList();
            if (!fileSearchBySizes.isEmpty()) {
                fileSearchBySizes.forEach(FileSearchBySize::fork);
                fileSearchBySizes.stream().map(FileSearchBySize::join).forEach(results::addAll);
            } else {
                return results;
            }
        }
        return results;
    }
}
