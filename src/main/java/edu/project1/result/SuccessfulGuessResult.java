package edu.project1.result;

import edu.project1.Word;

public final class SuccessfulGuessResult extends GuessResult {

    public SuccessfulGuessResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "Hit!";
    }
}
