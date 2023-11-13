package edu.hw5.Task3;

import edu.hw5.InputErrorException;
import java.time.LocalDate;
import java.util.Optional;
import static edu.hw5.Task3.WordDateFormatter.WordDate.TODAY;
import static edu.hw5.Task3.WordDateFormatter.WordDate.TOMORROW;
import static edu.hw5.Task3.WordDateFormatter.WordDate.YESTERDAY;

public class WordDateFormatter extends Formatter {
    public WordDateFormatter(Formatter next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> parseDate(String date) throws InputErrorException {
        isDateNull(date);
        if (date.equals(YESTERDAY.getWord())) {
            return Optional.of(LocalDate.now().minusDays(1));
        }
        if (date.equals(TODAY.getWord())) {
            return Optional.of(LocalDate.now());
        }
        if (date.equals(TOMORROW.getWord())) {
            return Optional.of(LocalDate.now().plusDays(1));
        }
        return Optional.empty();
    }

    public enum WordDate {
        TODAY("today"), YESTERDAY("yesterday"), TOMORROW("tomorrow");

        private final String word;

        WordDate(String word) {
            this.word = word;
        }

        public String getWord() {
            return word;
        }
    }
}
