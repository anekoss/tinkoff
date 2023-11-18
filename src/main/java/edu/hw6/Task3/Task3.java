package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {
    private Task3() {
    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException {
        if (Files.isDirectory(dir)) {
            return Files.newDirectoryStream(dir);

        } else {
            return Files.newDirectoryStream(dir.toAbsolutePath().getParent());

        }

    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob) throws IOException {
        if (Files.isDirectory(dir)) {
            return Files.newDirectoryStream(dir, DirectoryFilter.globMatches(glob));

        } else {
            return Files.newDirectoryStream(
                dir.toAbsolutePath().getParent(),
                DirectoryFilter.globMatches(glob)
            );
        }

    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir, AbstractFilter filter)
        throws IOException {
        if (Files.isDirectory(dir)) {
            return
                Files.newDirectoryStream(dir, filter);

        } else {
            return
                Files.newDirectoryStream(dir.toAbsolutePath().getParent(), filter);
        }
    }
}
