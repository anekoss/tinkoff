package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {

    private final Pattern patternOneSpecialSymbol = Pattern.compile("^[^~!@#$%^&*|]*[~!@#$%^&*|][^~!@#$%^&*|]*$");

    public boolean checkPasswordSecurity(String password) {
        if (password == null) {
            return false;
        }
        return patternOneSpecialSymbol.matcher(password).find();
    }
}
