package edu.project2.solver;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import java.util.List;

public class BSFSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze, Coordinate in, Coordinate out) {
        return new BFSUtil(maze).solve(in, out);
    }
}
