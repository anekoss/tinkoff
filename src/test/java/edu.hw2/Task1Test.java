package edu.hw2;

import edu.hw2.Task1.Addition;
import edu.hw2.Task1.Constant;
import edu.hw2.Task1.Exponent;

import edu.hw2.Task1.Multiplication;
import edu.hw2.Task1.Negate;
import org.assertj.core.data.Offset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(0.0, 30.0),
            Arguments.of(10.0, 0.0),
            Arguments.of(10.0, -140),
            Arguments.of(0.0, 0.0),
            Arguments.of(0.0, 373.0),
            Arguments.of(0.0, -130.0),
            Arguments.of(-12.0, -264.0),
            Arguments.of(-234.0, 0.0),
            Arguments.of(-234.0, 47.0)
        );

    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void constantAndNegateTest(double value1) {
        assertThat(new Constant(value1).evaluate()).isEqualTo(value1);
        assertThat(new Constant(new Constant(value1)).evaluate()).isEqualTo(value1);
        assertThat(new Negate(value1).evaluate()).isEqualTo(-value1);
        assertThat(new Negate(new Negate(value1)).evaluate()).isEqualTo(value1);
        assertThat(new Constant(new Negate(value1)).evaluate()).isEqualTo(-value1);
        assertThat(new Negate(new Constant(value1)).evaluate()).isEqualTo(-value1);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void multiplicationTest(double value1, double value2) {
        assertThat(new Multiplication(value1, value2).evaluate()).isEqualTo(value1 * value2);
        assertThat(new Multiplication(new Constant(value1), value2).evaluate()).isEqualTo(
            value1 * value2);
        assertThat(new Multiplication(value1, new Constant(value2)).evaluate()).isEqualTo(
            value1 * value2);
        assertThat(new Multiplication(new Constant(value1), new Constant(value2)).evaluate()).isEqualTo(
            value1 * value2);
        assertThat(new Multiplication(new Negate(value1), new Constant(value2)).evaluate()).isEqualTo(
            -value1 * value2);
        assertThat(new Multiplication(new Negate(value1), new Negate(value2)).evaluate()).isEqualTo(
            (-value1) * (-value2));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void exponentTest(double value1, double value2) {
        assertThat(new Exponent(value1, value2).evaluate()).isEqualTo(Math.pow(value1, value2));
        assertThat(new Exponent(new Constant(value1), value2).evaluate()).isEqualTo(Math.pow(value1, value2));

        assertThat(new Exponent(value1, new Constant(value2)).evaluate()).isEqualTo(
            Math.pow(value1, value2));
        assertThat(new Exponent(new Constant(value1), new Constant(value2)).evaluate()).isEqualTo(
            Math.pow(value1, value2));
        assertThat(new Exponent(new Negate(value1), new Constant(value2)).evaluate()).isEqualTo(
            Math.pow(-value1, value2));
        assertThat(new Exponent(new Constant(value1), new Negate(value2)).evaluate()).isEqualTo(
            Math.pow(value1, -value2));
        assertThat(new Exponent(new Negate(value1), new Negate(value2)).evaluate()).isEqualTo(
            Math.pow(-value1, -value2));

    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void additionTest(double value1, double value2) {

        assertThat(new Addition(value1, value2).evaluate()).isEqualTo(value1 + value2);
        assertThat(new Addition(new Constant(value1), value2).evaluate()).isEqualTo(
            value1 + value2);
        assertThat(new Addition(value1, new Constant(value2)).evaluate()).isEqualTo(
            value1 + value2);
        assertThat(new Addition(new Constant(value1), new Constant(value2)).evaluate()).isEqualTo(
            value1 + value2);
        assertThat(new Addition(new Negate(value1), new Constant(value2)).evaluate()).isEqualTo(
            -value1 + value2);
        assertThat(new Addition(new Negate(value1), new Negate(value2)).evaluate()).isEqualTo(
            -value1 - value2);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void complexTest(double value1, double value2) {
        assertThat(new Addition(new Exponent(value1, value2), new Multiplication(value2, value1)).evaluate()).isEqualTo(
            Math.pow(value1, value2) + value1 * value2);
        assertThat(new Multiplication(
            new Exponent(value1, value2),
            new Multiplication(value2, value1)
        ).evaluate()).isCloseTo(
            Math.pow(value1, value2) * (value1 * value2), Offset.offset(0.0000001));
        assertThat(new Exponent(new Exponent(value1, value2), new Multiplication(value2, value1)).evaluate()).isEqualTo(
            Math.pow(Math.pow(value1, value2), value1 * value2));
        assertThat(new Addition(
            new Multiplication(new Negate(value1), new Constant(value2)),
            new Exponent(value1, value2)
        ).evaluate()).isEqualTo(
            (-value1 * value2) + Math.pow(value1, value2));
    }

}
