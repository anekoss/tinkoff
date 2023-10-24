package edu.project1.result;

import edu.project1.WordGuess;

public final class RepeatGuessResult extends GuessResult {
    public RepeatGuessResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return "This guess has already been entered. Please enter another character.\n"
            + new DefaultResult(wordGuess).message();
    }
}
