package edu.project1;

import edu.project1.result.GuessResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Printer {

    public static final Logger LOGGER = LogManager.getLogger();

    public void printInvite(PlayersProvider playersProvider) {
        if (playersProvider.getSize() == 1) {
            LOGGER.info("Guess a letter:");
        } else {
            LOGGER.info(playersProvider.getCurrent().name(), " guess a letter:");
        }
    }

    public void printGuess(String guess) {
        LOGGER.info(guess);
    }

    public void printResult(GuessResult guessResult) {
        String[] result = guessResult.message().split("\n");
        for (String s : result) {
            LOGGER.info(s);
        }
        printEmpty();
    }

    void printEmpty() {
        LOGGER.info("");
    }
}
