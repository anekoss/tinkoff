package edu.project1;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void maxNumberAttemptsTest(int maxAttempts, String word, String scanner)
        throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();

        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), word, maxAttempts);
        game.play();
        assertThat(game.getWord().getAttempts()).isLessThanOrEqualTo(maxAttempts);
        String lastLog = logCaptor.getLogs().get(logCaptor.getLogs().size() - 1);
        assertThat(lastLog).isEqualTo("You lost!");
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
    void noGuessWordTest(int maxAttempts, String word, String scanner)
        throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), word, maxAttempts);
        game.play();
        String lastLog = logCaptor.getLogs().get(logCaptor.getLogs().size() - 1);
        assertThat(lastLog).isEqualTo("You lost!");
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
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), null, maxAttempts);
        Exception thrown = assertThrows(WrongWordException.class, game::play);
        assertEquals("The hidden word cannot be empty. Choose another word!", thrown.getMessage());
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
    void manyPlayerGameTest(int maxAttempts, String word, String scanner) throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getManyPlayersProvider(), new Scanner(scanner), word, maxAttempts);
        game.play();
        assertThat(game.getWord().getAttempts()).isLessThanOrEqualTo(maxAttempts);
        String lastLog = logCaptor.getLogs().get(logCaptor.getLogs().size() - 1);
        assertThat(lastLog).isEqualTo("You won!");
    }

    @ParameterizedTest
    @DisplayName(
        "При отгадывании ввод строки длиной больше чем 1 ('уу') приводит к повторному вводу, без использования попытки")
    @CsvSource({"3, клубника, к л уу р у б н и а ж х и е"
    })
    void IncorrectInputTest(int maxAttempts, String word, String scanner) throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), word, maxAttempts);
        game.play();
        assertThat(game.getWord().getAttempts()).isEqualTo(1);
        assertThat(logCaptor.getLogs().toString()).contains("Incorrect input, please enter 1 character!");
    }

    @ParameterizedTest
    @DisplayName("Отгадывание рандомно выбранного слова из словаря")
    @CsvSource({"3, m e y h t k n, You won!, 2",
        "3, m e y h q t k s, You lost!, 3",
        "3, m e yy h q t k s, You won!, 2",
        "3, m e yy j h q t k s, You lost!, 3"
    })
    void guessWordDictionaryTest(int maxAttempts, String scanner, String excepted, int expectedAttempts)
        throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), maxAttempts);
        game.play();
        assertThat(game.getWord().getAttempts()).isEqualTo(expectedAttempts);
        String lastLog = logCaptor.getLogs().get(logCaptor.getLogs().size() - 1);
        assertThat(lastLog).isEqualTo(excepted);
    }
}
