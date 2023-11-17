package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class EuropeanShortDateFormatter extends Formatter {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yy");

    public EuropeanShortDateFormatter(Formatter formatter) {
        super(formatter);
    }

    public EuropeanShortDateFormatter() {
    }

    @Override
    public Optional<LocalDate> checkPatternDate(String date) {
        try {
            return Optional.of(LocalDate.parse(date, dateFormatter));
        } catch (DateTimeParseException e) {
            if (next != null) {
                return next.checkPatternDate(date);
            }
            return Optional.empty();
        }
    }

}
