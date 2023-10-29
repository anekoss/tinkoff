package edu.hw3;

import edu.hw3.Task1.NullStringCodedException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw3.Task1.Task1.atbash;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task1Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
            ),
            Arguments.of("good ssskken ! fjjd! абвгде 0231", "tllw hhhppvm ! uqqw! абвгде 0231"),
            Arguments.of("8800555           35acde35 ", "8800555           35zxwv35 "),
            Arguments.of("sdjsjdj JD DSJ S d sDtHj15BЦЦЦfhdb", "hwqhqwq QW WHQ H w hWgSq15YЦЦЦuswy")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void atbashTest(String value, @NotNull String excepted) throws NullStringCodedException {
        assertThat(atbash(value)).hasSize(excepted.length()).isEqualTo(excepted);
    }

    @Test
    void atbashNullTest() throws NullStringCodedException {
        assertThat(atbash("")).isEmpty();
        assertThrows(NullStringCodedException.class, () -> atbash(null));
    }
}
