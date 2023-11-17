package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private final int countMonthInYear = 12;
    private final int dayOfAwfulFriday = 13;

    public List<LocalDate> getAllFridayForYear(int year) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate;
        for (int i = 1; i <= countMonthInYear; i++) {
            currentDate = LocalDate.of(year, i, dayOfAwfulFriday);
            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                dates.add(currentDate);
            }
        }
        return dates;
    }

    public LocalDate getNextFridayThirteen(LocalDate date) throws InputErrorException {
        if (date == null) {
            throw new InputErrorException();
        }
        LocalDate nextFriday = date;
        do {
            nextFriday = nextFriday.with(TemporalAdjusters.firstDayOfNextMonth())
                .with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
        } while (nextFriday.getDayOfMonth() != dayOfAwfulFriday);
        return nextFriday;

    }

}
