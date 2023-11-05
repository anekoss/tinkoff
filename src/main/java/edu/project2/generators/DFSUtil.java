package edu.project2.generators;

import edu.project2.BadFieldMazeException;
import edu.project2.game.Cell;
import edu.project2.game.MazeManager;
import java.util.LinkedList;

public class DFSUtil {
    private final MazeManager mazeManager;
    private final LinkedList<Cell> visitedCell;
    private final GeneratorUtil generatorUtil;

    public DFSUtil(int height, int width) throws BadFieldMazeException {
        this.mazeManager = new MazeManager(height, width);
        this.visitedCell = new LinkedList<>();
        this.generatorUtil = new GeneratorUtil(mazeManager);
    }

    public Cell[][] generateDFSGrid() {
        Cell currentCell = generatorUtil.visitRandomCell();
        visitedCell.push(currentCell);
        while (!mazeManager.noVisitedIsEmpty()) {
            if (mazeManager.isNoVisitedNeighboursIsEmpty(currentCell)) {
                currentCell = generatorUtil.removeNeighbourAndWall(currentCell);
                visitedCell.push(currentCell);
            } else if (!visitedCell.isEmpty()) {
                currentCell = visitedCell.pop();
            } else {
                currentCell = generatorUtil.visitRandomCell();
            }
        }
        return mazeManager.getGrid();
    }

//    private Cell visitRandomCell() {
//        Cell currentCell = mazeManager.getRandomNoVisitedCell();
//        mazeManager.removeVisited(currentCell);
//        return currentCell;
//    }
//
//    private Cell removeNeighbourWall(Cell currentCell) {
//        Cell randomNeighbour = mazeManager.getRandomNeighbour(currentCell);
//        mazeManager.removeVisited(randomNeighbour);
//        mazeManager.removeNeighbour(currentCell, randomNeighbour);
//        mazeManager.removeWall(currentCell, randomNeighbour);
//        return randomNeighbour;
//    }

}
