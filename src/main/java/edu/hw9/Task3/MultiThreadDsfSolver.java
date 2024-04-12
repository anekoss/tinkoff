package edu.hw9.Task3;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeManager;
import edu.project2.solver.SolverManager;
import java.util.Deque;
import java.util.concurrent.ForkJoinPool;

public class MultiThreadDsfSolver {

    private final Maze maze;
    private final SolverManager solverManager;

    public MultiThreadDsfSolver(Maze maze) {
        this.solverManager = new SolverManager(maze);
        this.maze = maze;
    }

    public Deque<Cell> solve(Coordinate inCoordinate, Coordinate outCoordinate) {
        DsfPathSearch dsfPathSearch = new DsfPathSearch(
            solverManager.getCellForCoordinate(inCoordinate), solverManager.getCellForCoordinate(outCoordinate),
            new MazeManager(maze)
        );
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(dsfPathSearch);
    }

}
