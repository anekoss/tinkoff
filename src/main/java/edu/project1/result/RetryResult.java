package edu.project1.result;

import edu.project1.Word;

public final class RetryResult extends GuessResult {
    public RetryResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "Incorrect input, please enter 1 character!";
    }
}
