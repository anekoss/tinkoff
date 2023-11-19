package edu.project3.args;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.args.FilePatternFilter.getPathsContainsRegex;

public class CommandLineArgsParser {

    private static final Pattern PATTERN_ARGS =
        Pattern.compile(
            "^(\s?)(--path)(\s?)(.*)(\s?)(--from?)(\s?)(.*)(\s?)(--to|) "
                + "(\s?)(.*)(\s?)(--format)(\s?)(markdown|adoc)(\s?)$");
    private static final Pattern PATTERN_URI = Pattern.compile("^(https?|ftp)://[^\\s/$.?#].\\S*$");

    private static final Pattern PATTERN_PATH = Pattern.compile("^(.+)/([^/]+)$");
    private static final int NUMBER_GROUP_PATH = 4;
    private static final int NUMBER_GROUP_FROM = 8;
    private static final int NUMBER_GROUP_TO = 12;
    private static final int NUMBER_GROUP_FORMAT = 16;

    private final Matcher matcherArgs;

    public CommandLineArgsParser(String[] args) {
        this.matcherArgs = PATTERN_ARGS.matcher(initInputArgs(args));
    }

    public ArgsRecord getArgs() {
        if (!matcherArgs.matches()) {
            throw new IllegalArgumentException("Неверный формат аргументов командной строки");
        }
        List<Path> paths = getPaths(matcherArgs.group(NUMBER_GROUP_PATH).split(" "));
        List<URI> uris = getURI(matcherArgs.group(NUMBER_GROUP_PATH).split(" "));
        checkPath(paths, uris);
        LocalDate from = getTime(matcherArgs.group(NUMBER_GROUP_FROM).replaceAll(" ", ""));
        LocalDate to = getTime(matcherArgs.group(NUMBER_GROUP_TO).replaceAll(" ", ""));
        FormatReport formatReport = getFormat(matcherArgs.group(NUMBER_GROUP_FORMAT));
        return new ArgsRecord(paths, uris, from, to, formatReport);
    }

    private void checkPath(List<Path> paths, List<URI> uris) {
        if (paths.size() + uris.size() == 0) {
            throw new IllegalArgumentException("Неверный формат пути к log файлу. Ожидается локальный шаблон или URL.");
        }
    }

    private LocalDate getTime(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    private List<Path> getPaths(String[] allPath) {
        List<Path> pathList = new ArrayList<>();
        for (String path : allPath) {
            if (PATTERN_PATH.matcher(path).find()) {
                Path of = Path.of(path);
                if (Files.exists(of)) {
                    pathList.add(of);
                }
            } else {
                List<Path> regexPath = getPathsContainsRegex(path);
                if (!regexPath.isEmpty()) {
                    pathList.addAll(regexPath);
                }
            }
        }
        return pathList;
    }

    private List<URI> getURI(String[] allPath) {
        List<URI> uriList = new ArrayList<>();
        for (String uri : allPath) {
            if (PATTERN_URI.matcher(uri).find()) {
                uriList.add(URI.create(uri));
            }
        }
        return uriList;
    }

    private String initInputArgs(String[] args) {
        return String.join(" ", args).replaceAll("\s+", " ");
    }

}
