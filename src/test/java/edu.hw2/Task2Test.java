package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    static Arguments[] provideDataForTest() {
        return new Arguments[] {
            Arguments.of(new Rectangle(), 10, 20, 200),
            Arguments.of(new Rectangle(), 20, 20, 400),

            Arguments.of(new Rectangle(10, 10), 30, 20, 600),
            Arguments.of(new Rectangle(10, 10), 20, 20, 400),

            Arguments.of(new Rectangle(10, 20), 30, 20, 600),
            Arguments.of(new Rectangle(10, 20), 30, 30, 900),

            Arguments.of(new Square(), 10, 20, 200),
            Arguments.of(new Square(), 10, 10, 100),

            Arguments.of(new Square(10, 10), 10, 20, 200),
            Arguments.of(new Square(10, 10), 30, 30, 900),

            Arguments.of(new Square(10, 40), 30, 30, 900),
            Arguments.of(new Square(10, 40), 20, 30, 600),

            Arguments.of(new Square(10), 30, 30, 900),
            Arguments.of(new Square(10), 30, 40, 1200),

        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void rectangleArea(Rectangle rect, int value1, int value2, int res) {
        rect.setWidth(value1);
        rect.setHeight(value2);
        assertThat(rect.area()).isEqualTo(res);
    }

}
