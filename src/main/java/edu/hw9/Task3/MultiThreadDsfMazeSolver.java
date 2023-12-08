package edu.hw9.Task3;

import edu.project2.BadCoordinateException;
import edu.project2.BadFieldMazeException;
import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.solver.Solver;
import java.util.Deque;

public class MultiThreadDsfMazeSolver implements Solver {
    @Override
    public Deque<Cell> solve(Maze maze, Coordinate in, Coordinate out)
        throws BadFieldMazeException, BadCoordinateException {
        validateCoordinateMaze(maze, in);
        validateCoordinateMaze(maze, out);
        return new MultiThreadDsfSolver(maze).solve(in, out);
    }
}
