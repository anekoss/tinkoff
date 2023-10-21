package edu.project1;

import nl.altindag.log.LogCaptor;
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
    void giveUpTest(int maxAttempts, String word, String scanner)
        throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), word, maxAttempts);
        game.play();
        assertThat(game.getWord().getAttempts()).isLessThanOrEqualTo(maxAttempts);
        String lastLog = logCaptor.getLogs().get(logCaptor.getLogs().size() - 1);
        assertThat(lastLog).isEqualTo("You give up!");
    }

    @ParameterizedTest
    @DisplayName("Игрок вводит один и тот же символ повторно")
    @CsvSource({"3, клубника, к я я л , 1",
        "3, клубника, к к л , 0"
    })
    void repeatGuessTest(int maxAttempts, String word, String scanner, int exceptedAttempts)
        throws WrongWordException {
        LogCaptor logCaptor = LogCaptor.forClass(Printer.class);
        logCaptor.setLogLevelToInfo();
        Game game = new Game(getOnePlayerProvider(), new Scanner(scanner), word, maxAttempts);
        game.play();
        assertThat(game.getWord().getAttempts()).isEqualTo(exceptedAttempts);
        assertThat(logCaptor.getLogs().toString()).contains("This guess has already been entered. Please enter another character.");
    }
}
