package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {

    public boolean checkIsSubstring(String substring, String string) throws InputErrorException {
        if (string == null || substring == null || substring.length() > string.length()) {
            throw new InputErrorException();
        }
        return Pattern.compile(substring).matcher(string).find();
    }
}
