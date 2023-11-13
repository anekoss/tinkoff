package edu.project2;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.solver.BSF.BSFMazeSolver;
import edu.project2.solver.DSF.DSFMazeSolver;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static edu.project2.game.Cell.Dictionary.CELL;
import static edu.project2.game.Cell.Dictionary.WALL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class SolverTest {
    DSFMazeSolver dsfSolver = new DSFMazeSolver();
    BSFMazeSolver bsfSolver = new BSFMazeSolver();

    static Stream<Arguments> provideDataForTest() {
        Cell.Dictionary[][] grid = new Cell.Dictionary[11][11];
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
        Cell[][] cellGrid = new Cell[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                Cell cell = new Cell(i, j, grid[i][j]);
                cellGrid[i][j] = cell;
            }
        }
        return Stream.of(
            Arguments.of(
                new Maze(11, 11, cellGrid), new Coordinate(1, 1), new Coordinate(3, 1),
                List.of(
                    new Cell(
                        3, 1, CELL),
                    new Cell(3, 2, CELL),
                    new Cell(3, 3, CELL),
                    new Cell(3, 4, CELL),
                    new Cell(3, 5, CELL),
                    new Cell(4, 5, CELL),
                    new Cell(5, 5, CELL),
                    new Cell(5, 6, CELL),
                    new Cell(5, 7, CELL),
                    new Cell(4, 7, CELL),
                    new Cell(3, 7, CELL),
                    new Cell(2, 7, CELL),
                    new Cell(1, 7, CELL),
                    new Cell(1, 6, CELL),
                    new Cell(1, 5, CELL),
                    new Cell(1, 4, CELL),
                    new Cell(1, 3, CELL),
                    new Cell(1, 2, CELL),
                    new Cell(1, 1, CELL)
                )
            )
            ,
            Arguments.of(
                new Maze(11, 11, cellGrid), new Coordinate(6, 1), new Coordinate(5, 3), List.of(
                    new Cell(5, 3, CELL),
                    new Cell(5, 2, CELL),
                    new Cell(5, 1, CELL),
                    new Cell(6, 1, CELL)
                ))
        );
    }

    @ParameterizedTest()
    @MethodSource("provideDataForTest")
    void checkSolverTest(Maze maze, Coordinate in, Coordinate out, List<Cell> excepted)
        throws BadCoordinateException {
        assertThat(dsfSolver.solve(maze, in, out).stream().toList()).isEqualTo(excepted);
        assertThat(bsfSolver.solve(maze, in, out).stream().toList()).isEqualTo(excepted);
    }

    @Test
    void checkSizeCoordinateTest() {
        Maze maze = Mockito.mock(Maze.class);
        when(maze.height()).thenReturn(1);
        when(maze.width()).thenReturn(1);
        assertThrows(
            BadCoordinateException.class,
            () -> dsfSolver.solve(maze, new Coordinate(5, 10), new Coordinate(1, 1))
        );
        assertThrows(
            BadCoordinateException.class,
            () -> bsfSolver.solve(maze, new Coordinate(5, 10), new Coordinate(1, 1))
        );
    }

}
