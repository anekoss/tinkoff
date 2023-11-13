package edu.hw5.Task3;

import edu.hw5.InputErrorException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class EuropeanDateFormatter extends Formatter {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public EuropeanDateFormatter(Formatter next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> parseDate(String date) throws InputErrorException {
        isDateNull(date);
        try {
            return Optional.of(LocalDate.parse(date, dateFormatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
