package edu.project2.solver;

import edu.project2.BadCoordinateException;
import edu.project2.BadFieldMazeException;
import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import java.util.List;

public interface Solver {

    List<Cell> solve(Maze maze, Coordinate in, Coordinate out) throws BadFieldMazeException, BadCoordinateException;

    default Coordinate validateCoordinateMaze(Maze maze, Coordinate coordinate) throws BadCoordinateException {
        if (coordinate.col() < 0 || coordinate.col() > maze.height() || coordinate.row() < 0
            || coordinate.row() > maze.width()) {
            throw new BadCoordinateException();
        }
        return coordinate;
    }
}
