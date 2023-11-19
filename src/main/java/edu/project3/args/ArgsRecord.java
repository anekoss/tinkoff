package edu.project3.args;

import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ArgsRecord(List<Path> paths, List<URI> uris, java.time.LocalDate from, java.time.LocalDate to,
                         FormatReport format) {

    public List<List<String>> toStringMetrics() {
        List<List<String>> args = new ArrayList<>();
        args.add(List.of(
            "Файл(ы)",
            paths.stream().map(Path::toString).collect(Collectors.joining(",")) +
                uris.stream().map(URI::toString).collect(Collectors.joining(","))
        ));
        if (from == null) {
            args.add(List.of("Начальная дата", ""));
        } else {
            args.add(List.of("Начальная дата", from.toString()));
        }
        if (to == null) {
            args.add(List.of("Конечная дата", ""));
        } else {
            args.add(List.of("Конечная дата", to.toString()));
        }
        return args;
    }

}
