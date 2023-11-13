package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private final Pattern patternCarNumber = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    public boolean checkCorrectNumber(String number) throws InputErrorException {
        if (number == null) {
            throw new InputErrorException();
        }
        return patternCarNumber.matcher(number).find();
    }
}
