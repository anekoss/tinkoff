package edu.project3.args;

import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record ArgsRecord(Set<Path> paths, Set<URI> uris, java.time.LocalDate from, java.time.LocalDate to,
                         FormatReport format) {
    private final static String DATE_FROM = "Начальная дата";
    private final static String DATE_TO = "Конечная дата";

    public List<List<String>> toStringMetrics() {
        List<List<String>> args = new ArrayList<>();
        args.add(List.of(
            "Файл(ы)",
            paths.stream().map(Path::toString).collect(Collectors.joining(","))
                + uris.stream().map(URI::toString).collect(Collectors.joining(","))
        ));
        args.add(List.of(DATE_FROM, Optional.ofNullable(from).map(Objects::toString).orElse("")));
        args.add(List.of(DATE_TO, Optional.ofNullable(to).map(Objects::toString).orElse("")));
        return args;
    }

}
