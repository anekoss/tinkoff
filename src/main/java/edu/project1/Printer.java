package edu.project1;

import edu.project1.result.DefaultResult;
import edu.project1.result.FailedGuessResult;
import edu.project1.result.GiveUpResult;
import edu.project1.result.LostResult;
import edu.project1.result.RepeatGuessResult;
import edu.project1.result.RetryResult;
import edu.project1.result.SuccessfulGuessResult;
import edu.project1.result.WinResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Printer {
    private Printer() {
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public static void printInvite(PlayersProvider playersProvider) {
        if (playersProvider.getSize() == 1) {
            LOGGER.info("Guess a letter:");
        } else {
            LOGGER.info(playersProvider.getCurrent().name() + " guess a letter:");
        }
    }

    public static void printGuess(String guess) {
        LOGGER.info(guess);
    }

    public static void printDefault(Word word) {
        String info = new DefaultResult(word).message();
        LOGGER.info(info);

    }

    public static void printFailedGuessResult(Word word) {
        String info = new FailedGuessResult(word).message();
        LOGGER.info(info);
        printEmptyString();
        printDefault(word);
    }

    public static void printGiveUpResult(Word word) {
        String info = new GiveUpResult(word).message();
        LOGGER.info(info);

    }

    public static void printLostResult(Word word) {
        printFailedGuessResult(word);
        LOGGER.info("");
        String info = new LostResult(word).message();
        LOGGER.info(info);
    }

    public static void printRetryResult(Word word) {
        String info = new RetryResult(word).message();
        LOGGER.info(info);
        printEmptyString();
        printDefault(word);
    }

    static void printSuccessfulResult(Word word) {
        String info = new SuccessfulGuessResult(word).message();
        LOGGER.info(info);
        printEmptyString();
        printDefault(word);
    }

    public static void printRepeatGuessResult(Word word) {
        String info = new RepeatGuessResult(word).message();
        LOGGER.info(info);
        printEmptyString();
        printDefault(word);
    }

    public static void printWinResult(Word word) {
        String info = new SuccessfulGuessResult(word).message();
        LOGGER.info(info);
        printEmptyString();
        info = new WinResult(word).message();
        LOGGER.info(info);
    }

    public static void printEmptyString() {
        LOGGER.info("");
    }
}
