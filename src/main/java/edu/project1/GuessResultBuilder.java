package edu.project1;

import edu.project1.result.FailedGuessResult;
import edu.project1.result.GiveUpResult;
import edu.project1.result.GuessResult;
import edu.project1.result.LostResult;
import edu.project1.result.RepeatGuessResult;
import edu.project1.result.RetryResult;
import edu.project1.result.SuccessfulGuessResult;
import edu.project1.result.WinResult;

public class GuessResultBuilder {

    public GuessResult build(Status status, WordGuess word) {
        return switch (status) {
            case GIVE_UP -> new GiveUpResult(word);
            case LOST -> new LostResult(word);
            case WON -> new WinResult(word);
            default -> buildCharacterResult(status, word);
        };
    }

    public GuessResult buildCharacterResult(Status status, WordGuess word) {
        return switch (status) {
            case REPEAT_GUESS -> new RepeatGuessResult(word);
            case NOT_GUESS -> new FailedGuessResult(word);
            case GUESS -> new SuccessfulGuessResult(word);
            default -> new RetryResult(word);
        };
    }

}
