package edu.project2;

import edu.project2.generators.DSF.DSFMazeGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DSFMazeGeneratorTest {
    DSFMazeGenerator dfsGenerator = new DSFMazeGenerator();

    @ParameterizedTest
    @CsvSource({"-1, -1",
        "0, 0",
        "-3, 5",
        "3, -5",
    })
    void incorrectSizeSideMazeTest(int height, int width) {
        assertThrows(BadFieldMazeException.class, () -> dfsGenerator.generateMaze(height, width));
    }

    @ParameterizedTest
    @CsvSource({"1, 1, 3, 3",
        "3, 3, 5, 5",
        "2, 5, 3, 7",
        "100,100,101,101",
    })
    void checkSizeSideMazeTest(int height, int width, int exceptedHeight, int exceptedWidth)
        throws BadFieldMazeException {
        assertThat(dfsGenerator.generateMaze(height, width).height()).isEqualTo(exceptedHeight);
        assertThat(dfsGenerator.generateMaze(height, width).width()).isEqualTo(exceptedWidth);
    }

}
