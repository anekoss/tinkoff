package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("неверный формат: значение часов меньше нуля")
    void minutesLessThanZero() {
        String str = "-1:24";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: значение часов не число")
    void minutesNotNumber() {
        String str = "sh:24";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: отсутствие значения часов")
    void minutesIsNull() {
        String str = ":24";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: отсутствие значения часов и минут")
    void minutesAndSecondNull() {
        String str = ":";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: значение минут не число")
    void secondNotNumber() {
        String str = "12:sh";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: значение минут меньше нуля")
    void secondLessThanNull() {
        String str = "12:-3";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: отсутствие значения минут")
    void secondIsNull() {
        String str = "14:";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: значение минут больше 59")
    void minutesEqualsSixty() {
        String str = "14:60";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("неверный формат: значение минут больше 59")
    void secondMoreThanSixty() {
        String str = "14:70";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }

    @Test
    @DisplayName("ОК")
    void correctMinutesAndSecond1() {
        String str = "14:00";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(14 * 60);
    }

    @Test
    @DisplayName("ОК")
    void correctMinutesAndSecond2() {
        String str = "14:15";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(14 * 60 + 15);
    }

    @Test
    @DisplayName("неверный формат: hh:mm")
    void IncorrectFormatNotTime() {
        String str = "1415";
        Integer length = Task1.minutesToSeconds(str);
        assertThat(length).isEqualTo(-1);
    }
}
