package edu.project2.generators;

import edu.project2.game.Cell;
import edu.project2.game.MazeManager;

public class GeneratorUtil {
    private final MazeManager mazeManager;

    public GeneratorUtil(MazeManager mazeManager) {
        this.mazeManager = mazeManager;
    }

    public Cell visitRandomCell() {
        Cell cell = mazeManager.getRandomNoVisitedCell();
        mazeManager.removeVisited(cell);
        return cell;
    }

    public Cell removeNeighbourAndWall(Cell cell) {
        Cell neighbour = mazeManager.getRandomNeighbour(cell);
        removeVisitedWall(cell, neighbour);
        mazeManager.removeNeighbour(cell, neighbour);
        return neighbour;
    }

    public void removeVisitedWall(Cell cell, Cell neighbour) {
        mazeManager.removeVisited(neighbour);
        mazeManager.removeWall(cell, neighbour);
    }
}
