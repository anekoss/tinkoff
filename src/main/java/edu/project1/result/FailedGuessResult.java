package edu.project1.result;

import edu.project1.WordGuess;

public final class FailedGuessResult extends GuessResult {

    public FailedGuessResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return "Missed, mistake " + wordGuess.getAttempts() + " out of " + wordGuess.getMaxAttempts() + ".\n"
            + new DefaultResult(wordGuess).message();
    }
}
