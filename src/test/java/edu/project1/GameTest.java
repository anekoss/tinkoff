package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameTest {
    static PlayersProvider getManyPlayersProvider() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Chet"));
        players.add(new Player("Pat"));
        players.add(new Player("Sue"));
        players.add(new Player("John"));
        return new PlayersProvider(players);
    }

    static PlayersProvider getOnePlayerProvider() {
        ArrayList<Player> player = new ArrayList<>();
        player.add(new Player("Sam"));
        return new PlayersProvider(player);
    }

    @ParameterizedTest
    @DisplayName("При достижении максимального количества попыток, игра возвращает поражение")
    @CsvSource({"1, клубника, к я л у п д б р о ж х и е",
        "2, клубника, к я л у п д б р о ж х и е",
        "3, клубника, к я л у п д б р о ж х и е",
        "5, клубника, к я л у п д б р о ж х и е",
        "6, клубника, к я л у п д б р о ж х и е",
        "7, клубника, к я л у п д б р о ж х и е",
        "8, клубника, к я л у п д б р о ж х и е"
    })
    void maxNumberAttemptsTest(int maxAttempts, String wordValue, String scanner) throws WrongWordException {
        Word word = new Word(wordValue);
        WordGuess wordGuess = new WordGuess(word, maxAttempts);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(wordGuess.checkAttemptsEnded()).isTrue();
        assertThat(statusGuess.getStatus()).isEqualTo(Status.LOST);
    }

    @ParameterizedTest
    @DisplayName("Игра возвращает поражение")
    @CsvSource({"1, клубника, к я л у п д б р о ж х и е",
        "2, клубника, к я л у п д б р о ж х и е",
        "3, клубника,к я л у п д б р о ж х и е",
        "5, клубника, к я л у п д б р о ж х и е",
        "6, клубника, к я л у п д б р о ж х и е",
        "7, клубника, к я л у п д б р о ж х и е",
        "8, клубника, к я л у п д б р о ж х и е"
    })
    void noGuessWordTest(int maxAttempts, String wordValue, String scanner)
        throws WrongWordException {
        Word word = new Word(wordValue);
        WordGuess wordGuess = new WordGuess(word, maxAttempts);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(statusGuess.getStatus()).isEqualTo(Status.LOST);
        assertThat(statusGuess.getGuessResult().message()).contains("You lost!");
    }

    @ParameterizedTest
    @DisplayName("Загаданное слово имеет некорректную длину")
    @CsvSource({" 1, к я л у п д б р о ж х и е",
        " 2, к я л у п д б р о ж х и е",
        " 3, к я л у п д б р о ж х и е",
        " 5, к я л у п д б р о ж х и е",
        " 6, к я л у п д б р о ж х и е",
        " 7, к я л у п д б р о ж х и е",
        " 8, к я л у п д б р о ж х и е"
    })
    void wordEmptyTest(int maxAttempts, String scanner) {
        Exception thrown = Assertions.assertThrows(
            WrongWordException.class,
            () -> new Game(
                getOnePlayerProvider(),
                new Scanner(scanner),
                new StatusGuess(new WordGuess("", maxAttempts))
            )
        );
        assertThat(thrown.getMessage()).isEqualTo("The hidden word cannot be empty. Choose another word!");
        Exception thrownNull = Assertions.assertThrows(
            WrongWordException.class,
            () -> new Game(getOnePlayerProvider(), new Scanner(scanner), new StatusGuess(new WordGuess(null)))
        );
        assertThat(thrownNull.getMessage()).isEqualTo("The hidden word cannot be empty. Choose another word!");
    }

    @ParameterizedTest
    @DisplayName("Игра на несколько игроков")
    @CsvSource({"1, клубника, к л у б н и а ж х и е",
        "2, клубника, к я л у б н и а ж х и е",
        "3, клубника, к я л у б н и а х и е",
        "5, клубника, к я л у б н и а ж х и е",
        "6, клубника, к я л у б н и а ж х и е",
        "7, клубника, к я л у б н и а х и е",
        "8, клубника, к я л у б н и а х и е"
    })
    void manyPlayerGameTest(int maxAttempts, String wordValue, String scanner) throws WrongWordException {
        Word word = new Word(wordValue);
        WordGuess wordGuess = new WordGuess(word, maxAttempts);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getManyPlayersProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(wordGuess.getAttempts()).isLessThanOrEqualTo(maxAttempts);
        assertThat(statusGuess.getStatus()).isEqualTo(Status.WON);
        assertThat(statusGuess.getGuessResult().message()).contains("You won!");
    }

    @ParameterizedTest
    @DisplayName(
        "При отгадывании ввод строки длиной больше чем 1 ('уу') приводит к повторному вводу, без использования попытки")
    @CsvSource({"3, клубника, к л уу р у б н и а ж х и е"
    })
    void IncorrectInputTest(int maxAttempts, String wordValue, String scanner) throws WrongWordException {
        Word word = new Word(wordValue);
        WordGuess wordGuess = new WordGuess(word, maxAttempts);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(statusGuess.getStatus()).isEqualTo(Status.WON);
        assertThat(wordGuess.getAttempts()).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Отгадывание рандомно выбранного слова из словаря")
    @CsvSource({"3, m e y h t k n, WON, 2",
        "3, m e y h q t k s, LOST, 3",
        "3, m e yy h q t k s, WON, 2",
        "3, m e yy j h q t k s, LOST, 3"
    })
    void guessWordDictionaryTest(int maxAttempts, String scanner, Status excepted, int expectedAttempts) {
        WordGuess wordGuess = new WordGuess(maxAttempts);
        StatusGuess statusGuess = new StatusGuess(wordGuess);
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), statusGuess);
        game.play();
        assertThat(wordGuess.getAttempts()).isEqualTo(expectedAttempts);
        assertThat(statusGuess.getStatus()).isEqualTo(excepted);
    }
}
