package edu.project1.result;

import edu.project1.WordGuess;

public final class RetryResult extends GuessResult {
    public RetryResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return "Incorrect input, please enter 1 character!\n"
            + new DefaultResult(wordGuess).message();
    }
}
