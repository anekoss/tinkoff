package edu.project2;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.generators.DSF.DSFMazeGenerator;
import edu.project2.solver.BSF.BSFMazeSolver;
import edu.project2.solver.DSF.DSFMazeSolver;
import java.util.Deque;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class GenerateAndSolverTest {
    DSFMazeGenerator dfsGenerator = new DSFMazeGenerator();
    DSFMazeSolver dsfSolver = new DSFMazeSolver();
    BSFMazeSolver bsfSolver = new BSFMazeSolver();

    @ParameterizedTest
    @CsvSource({"10, 10, 1, 1, 9,9",
        "9, 9, 1, 1, 5,5"})
    void generateAndSolveMazeTest(int height, int width, int inX, int inY, int outX, int outY)
        throws BadFieldMazeException, BadCoordinateException {
        Maze maze = dfsGenerator.generateMaze(height, width);
        Deque<Cell> path = dsfSolver.solve(maze, new Coordinate(inX, inY), new Coordinate(outX, outY));
        assertThat(path).isNotNull().contains(new Cell(inX, inY, Cell.Dictionary.CELL))
            .contains(new Cell(outX, outY, Cell.Dictionary.CELL));
        path = bsfSolver.solve(maze, new Coordinate(inX, inY), new Coordinate(outX, outY));
        assertThat(path).isNotNull().contains(new Cell(inX, inY, Cell.Dictionary.CELL))
            .contains(new Cell(outX, outY, Cell.Dictionary.CELL));

    }
}
