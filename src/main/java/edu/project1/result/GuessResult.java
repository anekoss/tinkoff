package edu.project1.result;

import edu.project1.WordGuess;

public sealed class GuessResult
    permits DefaultResult, FailedGuessResult, GiveUpResult, LostResult, RepeatGuessResult, RetryResult,
    SuccessfulGuessResult, WinResult {

    protected WordGuess wordGuess;

    public GuessResult(WordGuess wordGuess) {
        this.wordGuess = wordGuess;
    }

    public String message() {
        return "Hangman (game)";
    }
}
