package edu.project2.solver.DSF;

import edu.project2.BadCoordinateException;
import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.solver.Solver;
import java.util.Deque;
import java.util.List;

public class DSFSolver implements Solver {

    @Override
    public Deque<Cell> solve(Maze maze, Coordinate in, Coordinate out) throws BadCoordinateException {
        validateCoordinateMaze(maze, in);
        validateCoordinateMaze(maze, out);
        return new DSFUtil(maze).solve(in, out);
    }
}
