package edu.hw1;

import static edu.hw1.Task4.fixString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    void test1() {
        String s = "123456";
        String res = fixString(s);
        assertThat(res).isEqualTo("214365");
    }

    @Test
    void test2() {
        String s = "hTsii  s aimex dpus rtni.g";
        String res = fixString(s);
        assertThat(res).isEqualTo("This is a mixed up string.");
    }

    @Test
    void test3() {
        String s = "badce";
        String res = fixString(s);
        assertThat(res).isEqualTo("abcde");
    }

    @Test
    @DisplayName("пустая строка")
    void test4() {
        String s = "";
        String res = fixString(s);
        assertThat(res).isEqualTo("");
    }

    @Test
    void test5() {
        String s = "оПомигети псаривьтс ртко!и";
        String res = fixString(s);
        assertThat(res).isEqualTo("Помогите исправить строки!");
    }

}
