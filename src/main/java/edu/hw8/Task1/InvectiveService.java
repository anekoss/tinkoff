package edu.hw8.Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class InvectiveService {
    private final List<String> sensitiveList;
    private static final String DEFAULT_INVECTIVE_SENSITIVE = "Ты не хороший!";

    public InvectiveService(@NotNull Path path) throws IOException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IllegalArgumentException("file does not exist");
        }
        this.sensitiveList = Files.readAllLines(path);
    }

    public String getInvectiveSensitive(String word) {
        return sensitiveList.stream().filter(value -> hasWord(word, value)).findAny()
            .orElse(DEFAULT_INVECTIVE_SENSITIVE);
    }

    public boolean hasWord(@NotNull String word, @NotNull String sensitive) {
        if (word.isEmpty() || sensitive.isEmpty()) {
            return false;
        }
        return sensitive.contains(word);
    }

}
