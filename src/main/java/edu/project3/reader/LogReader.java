package edu.project3.reader;

import edu.project3.args.ArgsRecord;
import edu.project3.args.CommandLineArgsParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.stream.Stream;

public class LogReader {

    private final HttpClient httpClient;
    private final ArgsRecord argsRecord;
    private static final int TIMEOUT = 60;

    public LogReader(CommandLineArgsParser commandLineArgsParser) {
        argsRecord = commandLineArgsParser.getArgs();
        httpClient = HttpClient.newHttpClient();
    }

    public ArgsRecord getArgsRecord() {
        return argsRecord;
    }

    public Stream<String> getLogsStream() {
        return Stream.concat(
            argsRecord.paths().stream().filter(Objects::nonNull).flatMap(this::readLog),
            argsRecord.uris().stream().filter(Objects::nonNull).flatMap(value ->
            {
                try {
                    return readLog(value);
                } catch (InterruptedException e) {
                    throw new IllegalArgumentException();
                }
            })
        );
    }

    private Stream<String> readLog(Path path) {
        if (Files.exists(path)) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
                return reader.lines();
            } catch (IOException e) {
                return Stream.of();
            }
        }
        return Stream.of();
    }

    private Stream<String> readLog(URI path) throws InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(path)
            .timeout(Duration.of(TIMEOUT, ChronoUnit.SECONDS))
            .GET()
            .build();
        try {
            var response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofInputStream()
            );
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));
                return reader.lines();
            } catch (Exception e) {
                return Stream.of();
            }
        } catch (IOException e) {
            return Stream.of();
        }
    }


}
