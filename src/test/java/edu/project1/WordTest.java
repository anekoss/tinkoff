package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Scanner;
import static edu.project1.GameTest.getOnePlayerProvider;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WordTest {

    @ParameterizedTest
    @DisplayName("Угадывание рандомно выбранного слова из словаря c maxAttempts по умолчанию")
    @CsvSource({"m e e t, WON, 0",
        "m e y e t, LOST, 1"
    })
    void guessWordDictionaryTest(String scanner, Status excepted, int expectedAttempts) {
        WordGuess wordGuess = new WordGuess();
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(wordGuess.getAttempts()).isEqualTo(expectedAttempts);
        assertThat(statusGuess.getStatus()).isEqualTo(excepted);
    }

    @ParameterizedTest
    @DisplayName("Угадывание выбранного слова c maxAttempts по умолчанию")
    @CsvSource({"apple, a p p l e, WON, 0",
        "apple, a p l f l e, LOST, 1"
    })
    void guessWordTest(String wordValue, String scanner, Status excepted, int expectedAttempts)
        throws WrongWordException {
        WordGuess wordGuess = new WordGuess(wordValue);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(wordGuess.getAttempts()).isEqualTo(expectedAttempts);
        assertThat(statusGuess.getStatus()).isEqualTo(excepted);
    }
}
