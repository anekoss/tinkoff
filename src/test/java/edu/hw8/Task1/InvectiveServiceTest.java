package edu.hw8.Task1;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvectiveServiceTest {
    private static InvectiveService invectiveService;

    @BeforeAll
    static void init() throws IOException {
        invectiveService = new InvectiveService(Path.of("src/main/resources/h8/file.txt"));
    }

    @ParameterizedTest
    @CsvSource({"личности, 'Не переходи на личности там, где их нет'",
        "где, 'Не переходи на личности там, где их нет'",
        "'', Ты не хороший!",
        "ты, 'А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.'",
        "интеллект, 'Чем ниже интеллект, тем громче оскорбления'",
        "кошка, Ты не хороший!",
        "громче оскорбления, 'Чем ниже интеллект, тем громче оскорбления'"
    })
    void getInvectiveSensitiveTest(String word, String exceptedSensitive) {
        assertThat(invectiveService.getInvectiveSensitive(word)).isEqualTo(exceptedSensitive);
    }

    @ParameterizedTest
    @CsvSource({"личности, 'Не переходи на личности там, где их нет', true",
        "где, 'Не переходи на личности там, где их нет', true",
        "'', 'Не переходи на личности там, где их нет', false",
        "ты нехороший, '', false",
        "'', '', false",
        "ты нехороший, 'Не переходи на личности там, где их нет', false"})
    void hasWordTest(String word, String sensitive, boolean excepted) {
        assertThat(invectiveService.hasWord(word, sensitive)).isEqualTo(excepted);
    }

    @Test
    void readFileTest() {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> new InvectiveService(Path.of("\\")));
        assertThat(exception.getMessage()).isEqualTo("file does not exist");
    }

}
