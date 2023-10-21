package edu.project1.result;

import edu.project1.Word;

public final class FailedGuessResult extends GuessResult {

    public FailedGuessResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "Missed, mistake " + word.getAttempts() + " out of " + word.getMaxAttempts() + ".";
    }
}
