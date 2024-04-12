package edu.hw9.Task2;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Search {
    private final ForkJoinPool forkJoinPool;

    public Search(ForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    public Set<Path> searchDirectoryByMoreFilesCount(Path path, int count) {
        return forkJoinPool.invoke(new DirectorySearch(path, count));
    }

    public Set<Path> searchFileBySize(Path path, long fileSize) {
        return forkJoinPool.invoke(new FileSearchBySize(path, fileSize));
    }

    public Set<Path> searchFileByExtension(Path path, String extension) {
        return forkJoinPool.invoke(new FileSearchByExtension(path, extension));
    }
}
