package edu.hw6.Task4;

import edu.hw6.Task2.Task2;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.jetbrains.annotations.NotNull;

public class Task4 {
    public Path getFileUsingCompositionOutputStream(@NotNull String filePath, @NotNull String data) throws IOException {
        Path file = Path.of(filePath);
        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            file = Task2.cloneFile(file);
        }
        try (
            OutputStream outputStream = Files.newOutputStream(file);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                bufferedOutputStream,
                StandardCharsets.UTF_8
            );
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.write(data);
            return file;
        }
    }
}
