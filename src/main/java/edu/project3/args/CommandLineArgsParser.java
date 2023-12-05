package edu.project3.args;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.args.FilePatternFilter.getPathsContainsRegex;

public class CommandLineArgsParser {

    private static final Pattern PATTERN_ARGS =
        Pattern.compile(
            "^(\s?)(--path)(\s?)([^-]+)((--from)(\s?)([\\d,-]+))?(\s?)((--to)?(\s?)([\\d, -]+))?(\s?)"
                + "(--format)(\s?)(markdown|adoc)(\s?)$");

    private static final int NUMBER_GROUP_PATH = 4;
    private static final int NUMBER_GROUP_FROM = 8;
    private static final int NUMBER_GROUP_TO = 13;
    private static final int NUMBER_GROUP_FORMAT = 17;
    private final Matcher matcherArgs;

    public CommandLineArgsParser(String[] args) {
        this.matcherArgs = PATTERN_ARGS.matcher(initInputArgs(args));
    }

    public ArgsRecord getArgs() {
        if (!matcherArgs.matches()) {
            throw new IllegalArgumentException("Неверный формат аргументов командной строки");
        }
        Set<Path> paths = new HashSet<>();
        Set<URI> uris = new HashSet<>();
        for (String path : matcherArgs.group(NUMBER_GROUP_PATH).split(" ")) {
            if (isURI(path)) {
                uris.add(URI.create(path));
            } else if (isFilePath(path)) {
                paths.add(Path.of(path));
            } else {
                List<Path> regexPath = getPaths(path);
                if (regexPath.isEmpty()) {
                    throw new IllegalArgumentException(
                        "Неверный формат пути к log файлу. Ожидается локальный шаблон или URL.");
                } else {
                    paths.addAll(regexPath);
                }
            }
        }
        LocalDate from = getTime(matcherArgs.group(NUMBER_GROUP_FROM));
        LocalDate to = getTime(matcherArgs.group(NUMBER_GROUP_TO));

        FormatReport formatReport = getFormat(matcherArgs.group(NUMBER_GROUP_FORMAT));
        return new ArgsRecord(paths, uris, from, to, formatReport);
    }

    private LocalDate getTime(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(time.replaceAll(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат времени. Ожидается ISO8601.");
        }
    }

    private FormatReport getFormat(String format) {
        if (format.toLowerCase().equals(FormatReport.ADOC.getFormat())) {
            return FormatReport.ADOC;
        }
        if (format.toLowerCase().equals(FormatReport.MARKDOWN.getFormat())) {
            return FormatReport.MARKDOWN;
        } else {
            throw new IllegalArgumentException("Неверный формат. Ожидается markdown или adoc.");
        }
    }

    private boolean isFilePath(String path) {
        try {
            Path of = Path.of(path);
            if (Files.isDirectory(of)) {
                return false;
            }
            return Files.exists(of);
        } catch (InvalidPathException e) {
            return false;
        }
    }

    private List<Path> getPaths(String path) {
        List<Path> regexPath = getPathsContainsRegex(path);
        if (!regexPath.isEmpty()) {
            return regexPath;
        }
        return List.of();
    }

    private boolean isURI(String path) {
        try {
            new URL(path).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    private String initInputArgs(String[] args) {
        return String.join(" ", args);
    }

}
