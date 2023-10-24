package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Scanner;
import static edu.project1.GameTest.getOnePlayerProvider;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @ParameterizedTest
    @DisplayName("Игрок сдается")
    @CsvSource({"3, клубника, к я л у exit д б р о ж х и е",
    })
    void giveUpTest(int maxAttempts, String wordValue, String scanner)
        throws WrongWordException {
        Word word = new Word(wordValue);
        WordGuess wordGuess = new WordGuess(word, maxAttempts);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(wordGuess.getAttempts()).isLessThanOrEqualTo(maxAttempts);
        assertThat(statusGuess.getStatus()).isEqualTo(Status.GIVE_UP);
        assertThat(statusGuess.getGuessResult().message()).isEqualTo("You give up!");
    }

    @ParameterizedTest
    @DisplayName("Игрок вводит один и тот же символ повторно")
    @CsvSource({"3, клубника, к я я л , 1",
        "3, клубника, к к л , 0"
    })
    void repeatGuessTest(int maxAttempts, String wordValue, String scanner, int exceptedAttempts)
        throws WrongWordException {
        Word word = new Word(wordValue);
        WordGuess wordGuess = new WordGuess(word, maxAttempts);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(wordGuess.getAttempts()).isEqualTo(exceptedAttempts);
    }
}
