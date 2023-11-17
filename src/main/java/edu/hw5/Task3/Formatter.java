package edu.hw5.Task3;

import edu.hw5.InputErrorException;
import java.time.LocalDate;
import java.util.Optional;

public class Formatter {

    protected final Formatter next;

    public Formatter(Formatter next) {
        this.next = next;
    }

    public Formatter() {
        this.next = null;
    }

    public Optional<LocalDate> parseDate(String date) throws InputErrorException {
        isDateNull(date);
        return checkPatternDate(date);
    }

    public Optional<LocalDate> checkPatternDate(String date) {
        if (next != null) {
            return next.checkPatternDate(date);
        }
        return Optional.empty();
    }

    protected void isDateNull(String date) throws InputErrorException {
        if (date == null) {
            throw new InputErrorException();
        }
    }
}
