package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z",
        Locale.US
    );

    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile(
        "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"$");

    Stream<LogRecord> parseLogs(Stream<String> logStream) {
        return
            logStream.filter(s -> LOG_ENTRY_PATTERN.matcher(s).matches()).map(s -> {
                try {
                    return parseLog(s);
                } catch (InvalidLogFormat e) {
                    return null;
                }
            });
    }

    LogRecord parseLog(String log) throws InvalidLogFormat {
        Matcher matcher = LOG_ENTRY_PATTERN.matcher(log);
        if (matcher.matches()) {
            return new LogRecord(
                matcher.group(1),
                OffsetDateTime.parse(matcher.group(4), FORMATTER),
                matcher.group(5),
                Integer.parseInt(matcher.group(6)),
                Long.parseLong(matcher.group(7)),
                matcher.group(8),
                matcher.group(9)
            );
        } else {
            throw new InvalidLogFormat();
        }
    }
}
