package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class Task2 {
    private Task2() {
    }

    public static Path cloneFile(@NotNull Path path) throws IOException {
        String pathName = path.toString();
        String newPath;
        Path copyPath = path;
        int copyCount = 0;
        while (copyCount != -1) {
            String[] pathInfo = getFileNameAndGlob(pathName);
            if (copyCount == 0) {
                newPath =
                    pathInfo[0] + " — копия"
                        + pathInfo[1];
            } else {
                newPath =
                    pathInfo[0] + " — копия ("
                        + copyCount + ")"
                        + pathInfo[1];
            }
            try {
                copyCount = -1;
                copyPath = Files.createFile(Path.of(newPath));
            } catch (FileAlreadyExistsException ignored) {
                copyCount++;
            }
        }
        return copyPath;
    }

    private static String[] getFileNameAndGlob(String path) {
        int index = path.lastIndexOf(".");
        if (index != -1) {
            return new String[] {path.substring(0, index), path.substring(
                index)};
        } else {
            return new String[] {path, ""};
        }
    }
}
