package edu.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LogReader {

    Stream<String> readLog(Path path) {
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

    public static void main(String[] args) {
        Stream<LogRecord> logRecordStream = new LogParser().parseLogs(new LogReader().readLog(Path.of(
            "raw.githubusercontent.com_elastic_examples_master_Common Data Formats_nginx_logs_nginx_logs.txt")));
        logRecordStream.forEach(logRecord -> System.out.println(logRecord.toString()));
    }
}
