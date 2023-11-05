package edu.project2.generators;

import edu.project2.BadFieldMazeException;
import edu.project2.game.Cell;
import edu.project2.game.MazeManager;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TreeGeneratorUtil {
    private final MazeManager mazeManager;
    private final Set<NeighboursCell> neighbours;
    Iterator<NeighboursCell> iterator;
    private final GeneratorUtil generatorUtil;

    public TreeGeneratorUtil(int height, int width) throws BadFieldMazeException {
        this.mazeManager = new MazeManager(height, width);
        this.neighbours = new TreeSet<>(Comparator.comparing(NeighboursCell::getWeight));
        this.iterator = neighbours.iterator();
        generatorUtil = new GeneratorUtil(mazeManager);
    }

    public void addNoVisitNeighbours(Cell cell) {
        mazeManager.getNeighbours(cell)
            .forEach(neighbourCell -> neighbours.add(new NeighboursCell(cell, neighbourCell)));
    }

    public void addAllNoVisitNeighbours() {
        Cell randomCell = generatorUtil.visitRandomCell();
        addNoVisitNeighbours(randomCell);
        mazeManager.getNoVisitedCell().forEach(cell -> addNoVisitNeighbours(cell));
    }

    public Cell[][] generateKruskalGrid() {
        Cell currentCell = generatorUtil.visitRandomCell();
        addNoVisitNeighbours(currentCell);
        while (!neighbours.isEmpty()) {
            haveNoVisitedNeighbour();
        }
        return mazeManager.getGrid();
    }

    public Cell[][] generatePrimGrid() {
        addAllNoVisitNeighbours();
        while (!mazeManager.noVisitedIsEmpty()) {
            if (!iterator.hasNext()) {
                iterator = neighbours.iterator();
            }
            NeighboursCell neighboursCell = iterator.next();
            haveOneNoVisitedNeighbour(neighboursCell.getCell1(), neighboursCell.getCell2());
        }
        return mazeManager.getGrid();
    }

    public void haveNoVisitedNeighbour() {
        NeighboursCell neighboursCell = neighbours.iterator().next();
        Cell cell1 = neighboursCell.getCell1();
        Cell cell2 = neighboursCell.getCell2();
        if (mazeManager.isCellVisited(cell1) ||
            mazeManager.isCellVisited(cell2)) {
            generatorUtil.removeVisitedWall(cell1, cell2);
            addNoVisitNeighbours(cell1);
            addNoVisitNeighbours(cell2);
        } else {
            neighbours.remove(neighboursCell);
        }
    }

    private void haveOneNoVisitedNeighbour(Cell cell, Cell neighbour) {
        if (mazeManager.isCellVisited(cell) &&
            !mazeManager.isCellVisited(neighbour)) {
            generatorUtil.removeVisitedWall(neighbour, cell);
        } else if (mazeManager.isCellVisited(neighbour) &&
            !mazeManager.isCellVisited(cell)) {
            generatorUtil.removeVisitedWall(cell, neighbour);
        }
    }

}
