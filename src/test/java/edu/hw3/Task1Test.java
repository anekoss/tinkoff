package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.atbash;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    void atbashTest() {
        assertThat(atbash("Hello world!")).isEqualTo("Svool dliow!");
        assertThat(atbash(
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler")).isEqualTo(
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
        assertThat(atbash("")).isEqualTo("");
    }
}
