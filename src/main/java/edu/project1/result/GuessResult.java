package edu.project1.result;

import edu.project1.Word;

sealed class GuessResult
    permits DefaultResult, FailedGuessResult, GiveUpResult, LostResult, RepeatGuessResult, RetryResult,
    SuccessfulGuessResult, WinResult {

    protected Word word;

    GuessResult(Word word) {
        this.word = word;
    }

    public String message() {
        return "Hangman (game)";
    }
}
