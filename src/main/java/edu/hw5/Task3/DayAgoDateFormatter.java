package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class DayAgoDateFormatter extends Formatter {

    private final Pattern datePattern = Pattern.compile("^[-+]?\\d+ (days|day)? ago$");

    public DayAgoDateFormatter(Formatter formatter) {
        super(formatter);
    }

    public DayAgoDateFormatter() {
    }

    @Override
    public Optional<LocalDate> checkPatternDate(String date) {
        if (datePattern.matcher(date).find()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(date.split(" ")[0])));
        }
        if (next != null) {
            return next.checkPatternDate(date);
        }
        return Optional.empty();
    }
}
