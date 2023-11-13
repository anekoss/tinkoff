package edu.hw5.Task3;

import edu.hw5.InputErrorException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class DayAgoDateFormatter extends Formatter {

    private final Pattern datePattern = Pattern.compile("^[-+]?\\d+ (days,day)? ago$");

    public DayAgoDateFormatter(Formatter next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> parseDate(String date) throws InputErrorException {
        isDateNull(date);
        if (datePattern.matcher(date).find()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(date.split(" ")[0])));
        }
        return Optional.empty();
    }
}
