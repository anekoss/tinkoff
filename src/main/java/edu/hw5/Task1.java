package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.OptionalDouble;

public class Task1 {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    public Duration getSessionTime(List<String> sessionTime) throws InputErrorException {
        if (sessionTime == null || sessionTime.isEmpty()) {
            throw new InputErrorException();
        }
        try {
            OptionalDouble res = sessionTime.stream()
                .map(s -> {
                        String[] time = s.split(" - ");
                        return Duration.between(
                            LocalDateTime.parse(time[0], dateTimeFormatter),
                            LocalDateTime.parse(time[1], dateTimeFormatter)
                        );
                    }
                ).mapToLong(Duration::toMinutes).average();
            return Duration.ofMinutes(Math.round(res.getAsDouble()));
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InputErrorException();
        }
    }
}
