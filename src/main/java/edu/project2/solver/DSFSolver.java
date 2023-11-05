package edu.project2.solver;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import java.util.List;

public class DSFSolver implements Solver {

    public List<Cell> solve(Maze maze, Coordinate in, Coordinate out) {

        return new DSFUtil(maze).solve(in, out);
    }
}
