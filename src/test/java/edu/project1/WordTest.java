package edu.project1;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Scanner;
import static edu.project1.GameTest.getOnePlayerProvider;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WordTest {

    @ParameterizedTest
    @DisplayName("Угадывание рандомно выбранного слова из словаря c maxAttempts по умолчанию")
    @CsvSource({"m e e t, You won!, 0",
        "m e y e t, You lost!, 1"
    })
    void guessWordDictionaryTest(String scanner, String excepted, int expectedAttempts)
        throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner));
        game.play();
        assertThat(game.getWord().getAttempts()).isEqualTo(expectedAttempts);
        String lastLog = logCaptor.getLogs().get(logCaptor.getLogs().size() - 1);
        assertThat(lastLog).isEqualTo(excepted);
    }

    @ParameterizedTest
    @DisplayName("Угадывание выбранного слова c maxAttempts по умолчанию")
    @CsvSource({"apple, a p p l e, You won!, 0",
        "apple, a p l f l e, You lost!, 1"
    })
    void guessWordTest(String word, String scanner, String excepted, int expectedAttempts)
        throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), word);
        game.play();
        assertThat(game.getWord().getAttempts()).isEqualTo(expectedAttempts);
        String lastLog = logCaptor.getLogs().get(logCaptor.getLogs().size() - 1);
        assertThat(lastLog).isEqualTo(excepted);
    }
}
