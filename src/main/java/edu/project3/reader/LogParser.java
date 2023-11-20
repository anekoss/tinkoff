package edu.project3.reader;

import edu.project3.args.ArgsRecord;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {
    private static final int NUMBER_GROUP_IP_ADDRESS = 1;
    private static final int NUMBER_GROUP_TIMESTAMP = 4;
    private static final int NUMBER_GROUP_REQUEST = 5;
    private static final int NUMBER_GROUP_STATUS_CODE = 6;
    private static final int NUMBER_GROUP_RESPONSE_SIZE = 7;
    private static final int NUMBER_GROUP_REFERER = 8;
    private static final int NUMBER_GROUP_USER_AGENT = 9;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z",
        Locale.US
    );

    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile(
        "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})] \"(.+?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"$");

    public Stream<LogRecord> parseLogs(Stream<String> logStream, ArgsRecord argsRecord) {
        return
            logStream.filter(s -> LOG_ENTRY_PATTERN.matcher(s).matches()).map(this::parseLog)
                .filter(logRecord ->
                    (argsRecord.to() == null || logRecord.timestamp().toLocalDate().isBefore(argsRecord.to())
                        || logRecord.timestamp().toLocalDate().isEqual(argsRecord.to()))
                        && (argsRecord.from() == null || logRecord.timestamp().toLocalDate().isAfter(argsRecord.from())
                        || logRecord.timestamp().toLocalDate().isEqual(argsRecord.from())));
    }

    private LogRecord parseLog(String log) {
        Matcher matcher = LOG_ENTRY_PATTERN.matcher(log);
        if (matcher.matches()) {
            return new LogRecord(
                matcher.group(NUMBER_GROUP_IP_ADDRESS),
                OffsetDateTime.parse(matcher.group(NUMBER_GROUP_TIMESTAMP), FORMATTER),
                matcher.group(NUMBER_GROUP_REQUEST),
                Integer.parseInt(matcher.group(NUMBER_GROUP_STATUS_CODE)),
                Long.parseLong(matcher.group(NUMBER_GROUP_RESPONSE_SIZE)),
                matcher.group(NUMBER_GROUP_REFERER),
                matcher.group(NUMBER_GROUP_USER_AGENT)
            );
        } else {
            throw new IllegalArgumentException();
        }
    }
}
