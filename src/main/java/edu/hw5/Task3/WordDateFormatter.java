package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import static edu.hw5.Task3.WordDateFormatter.WordDate.TODAY;
import static edu.hw5.Task3.WordDateFormatter.WordDate.TOMORROW;
import static edu.hw5.Task3.WordDateFormatter.WordDate.YESTERDAY;

public class WordDateFormatter extends Formatter {

    public WordDateFormatter(Formatter formatter) {
        super(formatter);
    }

    public WordDateFormatter() {
    }

    @Override
    public Optional<LocalDate> checkPatternDate(String date) {
        Optional<LocalDate> localDate = Optional.empty();
        if (YESTERDAY.getWord().equals(date)) {
            localDate = Optional.of(LocalDate.now().minusDays(1));
        }
        if (TODAY.getWord().equals(date)) {
            localDate = Optional.of(LocalDate.now());
        }
        if (TOMORROW.getWord().equals(date)) {
            localDate = Optional.of(LocalDate.now().plusDays(1));
        }
        if (localDate.isPresent()) {
            return localDate;
        }
        if (next != null) {
            return next.checkPatternDate(date);
        }
        return Optional.empty();
    }

    enum WordDate {
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
