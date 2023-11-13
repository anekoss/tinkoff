package edu.project2;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeRenderer;
import edu.project2.game.Output;
import edu.project2.solver.DSF.DSFMazeSolver;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import static edu.project2.game.Cell.Dictionary.CELL;
import static edu.project2.game.Cell.Dictionary.WALL;
import static edu.project2.game.MazeRenderer.ANSI_GREEN_BACKGROUND;
import static edu.project2.game.MazeRenderer.ANSI_NEXT_LINE;
import static edu.project2.game.MazeRenderer.ANSI_RED_BACKGROUND;
import static edu.project2.game.MazeRenderer.ANSI_RESET;
import static edu.project2.game.MazeRenderer.ANSI_TWO_WHITESPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

class RendererTest {
    @Captor
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

    static Stream<Arguments> provideDataForTest() {
        Cell.Dictionary[][] grid = new Cell.Dictionary[11][13];
        grid[0] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL};
        grid[1] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, CELL, CELL, CELL, CELL, CELL, CELL, CELL, CELL, WALL};
        grid[2] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL, WALL, WALL, CELL, WALL, CELL, WALL, WALL, WALL};
        grid[3] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, CELL, CELL, WALL, CELL, WALL, CELL, WALL, CELL, WALL};
        grid[4] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL, CELL, WALL, CELL, WALL, CELL, WALL, CELL, WALL};
        grid[5] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, WALL, CELL, CELL, CELL, WALL, CELL, WALL, CELL, WALL};
        grid[6] = new Cell.Dictionary[] {WALL, CELL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, CELL, WALL, CELL, WALL};
        grid[7] = new Cell.Dictionary[] {WALL, CELL, WALL, CELL, CELL, CELL, CELL, CELL, CELL, CELL, WALL, CELL, WALL};
        grid[8] = new Cell.Dictionary[] {WALL, CELL, WALL, CELL, WALL, CELL, WALL, WALL, WALL, CELL, WALL, CELL, WALL};
        grid[9] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, WALL, CELL, CELL, CELL, CELL, CELL, CELL, CELL, WALL};
        grid[10] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL};
        Cell[][] cellGrid = new Cell[11][13];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                Cell cell = new Cell(i, j, grid[i][j]);
                cellGrid[i][j] = cell;
            }
        }
        return Stream.of(
            Arguments.of(
                new Maze(11, 13, cellGrid), 154));
    }

    static Stream<Arguments> provideDataForCheckOutputTest() {
        Cell.Dictionary[][] grid = new Cell.Dictionary[5][5];
        grid[0] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL};
        grid[1] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, WALL};
        grid[2] = new Cell.Dictionary[] {WALL, WALL, WALL, CELL, WALL};
        grid[3] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, WALL};
        grid[4] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL};
        Cell[][] cellGrid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Cell cell = new Cell(i, j, grid[i][j]);
                cellGrid[i][j] = cell;
            }
        }
        return Stream.of(
            Arguments.of(
                new Maze(5, 5, cellGrid), 30,
                ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_TWO_WHITESPACE +
                    ANSI_TWO_WHITESPACE +
                    ANSI_TWO_WHITESPACE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_TWO_WHITESPACE +
                    ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_TWO_WHITESPACE +
                    ANSI_TWO_WHITESPACE +
                    ANSI_TWO_WHITESPACE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_NEXT_LINE
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void checkCallOutputRenderTest(Maze maze, int exceptedTimes) {
        Output output = Mockito.mock(Output.class);
        MazeRenderer mazeRenderer = new MazeRenderer(output);
        mazeRenderer.printMaze(maze);
        Mockito.verify(output, times((exceptedTimes))).print(captor.capture());
        captor.getAllValues();
        assertThat(captor.getAllValues()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("provideDataForCheckOutputTest")
    void checkCorrectOutputRenderTest(Maze maze, int exceptedTimes, String excepted) {
        Output output = Mockito.mock(Output.class);
        MazeRenderer mazeRenderer = new MazeRenderer(output);
        mazeRenderer.printMaze(maze);
        Mockito.verify(output, times((exceptedTimes))).print(captor.capture());
        assertThat(captor.getAllValues()).isNotNull();
        String actual = String.join("", captor.getAllValues());
        assertThat(actual).isEqualTo(excepted);
    }

    static Stream<Arguments> provideDataForSolverOutputTest() {
        Cell.Dictionary[][] grid = new Cell.Dictionary[5][5];
        grid[0] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL};
        grid[1] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, WALL};
        grid[2] = new Cell.Dictionary[] {WALL, WALL, WALL, CELL, WALL};
        grid[3] = new Cell.Dictionary[] {WALL, CELL, CELL, CELL, WALL};
        grid[4] = new Cell.Dictionary[] {WALL, WALL, WALL, WALL, WALL};
        Cell[][] cellGrid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Cell cell = new Cell(i, j, grid[i][j]);
                cellGrid[i][j] = cell;
            }
        }
        return Stream.of(
            Arguments.of(
                new Maze(5, 5, cellGrid), 30, new Coordinate(1, 1), new Coordinate(3, 1),
                ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_RED_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_RED_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_RED_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_RED_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_RED_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_RED_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_RED_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE +
                    ANSI_RESET +
                    ANSI_NEXT_LINE +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_GREEN_BACKGROUND +
                    ANSI_TWO_WHITESPACE + ANSI_RESET +
                    ANSI_GREEN_BACKGROUND + ANSI_TWO_WHITESPACE + ANSI_RESET + ANSI_NEXT_LINE
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForSolverOutputTest")
    void checkCorrectSolveRenderTest(Maze maze, int exceptedTimes, Coordinate in, Coordinate out, String excepted)
        throws BadCoordinateException {
        Output output = Mockito.mock(Output.class);
        MazeRenderer mazeRenderer = new MazeRenderer(output);
        mazeRenderer.printSolve(maze, new DSFMazeSolver().solve(maze, in, out));
        Mockito.verify(output, times((exceptedTimes))).print(captor.capture());
        assertThat(captor.getAllValues()).isNotNull();
        String actual = String.join("", captor.getAllValues());
        assertThat(actual).isEqualTo(excepted);
    }

}

