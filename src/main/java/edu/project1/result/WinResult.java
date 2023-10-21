package edu.project1.result;

import edu.project1.Word;

public final class WinResult extends GuessResult {

    public WinResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "You won!";
    }
}
