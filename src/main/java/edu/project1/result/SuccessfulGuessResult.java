package edu.project1.result;

import edu.project1.WordGuess;

public final class SuccessfulGuessResult extends GuessResult {

    public SuccessfulGuessResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return "Hit!\n"
            + new DefaultResult(wordGuess).message();
    }
}
