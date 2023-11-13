package edu.hw5.Task3;

import edu.hw5.InputErrorException;
import java.time.LocalDate;
import java.util.List;
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
        Optional<LocalDate> localDate = Optional.empty();
        Formatter nextFormatter = next;
        while (nextFormatter != null && localDate.isEmpty()) {
            localDate = next.parseDate(date);
            nextFormatter = nextFormatter.next;
        }
        return localDate;
    }

    protected boolean isDateNull(String date) throws InputErrorException {
        if (date == null) {
            throw new InputErrorException();
        }
        return true;
    }
}
