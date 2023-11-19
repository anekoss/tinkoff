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

    private static final Pattern patternArgs =
        Pattern.compile(
            "^(\s?)(--path)(\s?)(.*)(\s?)(--from?)(\s?)(.*)(\s?)(--to|)(\s?)(.*)(\s?)(--format)(\s?)(markdown|adoc)(\s?)$");
    private static final Pattern patternURI = Pattern.compile("^(https?|ftp)://[^\\s/$.?#].\\S*$");

    private static final Pattern patternPath = Pattern.compile("^(.+)/([^/]+)$");

    private final Matcher matcherArgs;

    public CommandLineArgsParser(String[] args) {
        this.matcherArgs = patternArgs.matcher(initInputArgs(args));
        System.out.println(matcherArgs);
    }

    public ArgsRecord getArgs() {
        if (!matcherArgs.matches()) {
            throw new IllegalArgumentException("Неверный формат аргументов командной строки");
        }
        List<Path> paths = getPaths(matcherArgs.group(4).split(" "));
        List<URI> uris = getURI(matcherArgs.group(4).split(" "));
        checkPath(paths, uris);
        LocalDate from = getTime(matcherArgs.group(8).replaceAll(" ", ""));
        LocalDate to = getTime(matcherArgs.group(12).replaceAll(" ", ""));
        FormatReport formatReport = getFormat(matcherArgs.group(16));
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
            if (patternPath.matcher(path).find()) {
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
            if (patternURI.matcher(uri).find()) {
                uriList.add(URI.create(uri));
            }
        }
        return uriList;
    }

    private String initInputArgs(String[] args) {
        return String.join(" ", args).replaceAll("\s+", " ");
    }

}
