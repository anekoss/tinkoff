package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task5.isPalindromeDescendant;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task5Test {
    @Test
    void test1() {
        int n = 11211230;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(true);
    }

    @Test
    void test2() {
        int n = 13001120;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(true);
    }

    @Test
    void test3() {
        int n = 23336014;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(true);
    }

    @Test
    void test4() {
        int n = 11;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(true);
    }

    @Test
    void test5() {
        int n = 121;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(true);
    }

    @Test
    void test6() {
        int n = 123312;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(true);
    }

    @Test
    void test7() {
        int n = 112;
        boolean res = isPalindromeDescendant(n);

        assertThat(res).isEqualTo(true);
    }

    @Test
    void test8() {
        int n = 1124;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(false);
    }

    @Test
    void test9() {
        int n = 37;
        boolean res = isPalindromeDescendant(n);
        assertThat(res).isEqualTo(false);
    }

}
