package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {

    private final Pattern oddLength = Pattern.compile("^[0,1]((00)|(01)|(10)|(11))*$");
    private final Pattern oddLengthStartOneOrEvenLengthStartZero =
        Pattern.compile("^(0((00)|(01)|(10)|(11))*|1[0,1]((00)|(01)|(10)|(11))*)$");

    public boolean checkOddLength(String string) throws InputErrorException {
        if (string == null) {
            throw new InputErrorException();
        }
        return oddLength.matcher(string).find();
    }

    public boolean checkOddLengthStartOneOrEvenLengthStartZero(String string) throws InputErrorException {
        if (string == null) {
            throw new InputErrorException();
        }
        return oddLengthStartOneOrEvenLengthStartZero.matcher(string).find();
    }
}
