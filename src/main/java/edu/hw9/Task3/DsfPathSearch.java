package edu.hw9.Task3;

import edu.project2.game.Cell;
import edu.project2.game.MazeManager;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;
import org.jetbrains.annotations.NotNull;

public class DsfPathSearch extends RecursiveTask<Deque<Cell>> {
    private final Cell inCell;
    private final Cell outCell;
    private final MazeManager mazeManager;

    public DsfPathSearch(Cell inCell, Cell outCell, MazeManager mazeManager) {
        this.inCell = inCell;
        this.outCell = outCell;
        this.mazeManager = mazeManager;
    }

    @Override
    protected Deque<Cell> compute() {
        Deque<Cell> path = new ArrayDeque<>();
        path.push(inCell);
        if (inCell.equals(outCell)) {
            return path;
        }
        List<DsfPathSearch> dsfPathSearches = new ArrayList<>();
        while (mazeManager.isNoVisitedNeighboursIsEmpty(inCell)) {
            Cell currentCell = visitRandomNeighbour(inCell);
            DsfPathSearch dsfPathSearch = new DsfPathSearch(currentCell, outCell, mazeManager);
            dsfPathSearches.add(dsfPathSearch);
            dsfPathSearch.fork();
        }
        return getDsfPath(dsfPathSearches, path);
    }

    private Deque<Cell> getDsfPath(@NotNull List<DsfPathSearch> dsfPathSearches, Deque<Cell> path) {
        if (!dsfPathSearches.isEmpty()) {
            Optional<Deque<Cell>> optionalDsfPath = dsfPathSearches.stream().map(DsfPathSearch::join)
                .filter(p -> !p.isEmpty() && p.peek().equals(outCell)).findAny();
            if (optionalDsfPath.isPresent()) {
                Deque<Cell> dsfPath = optionalDsfPath.get();
                dsfPath.addAll(path);
                return dsfPath;
            }
        }
        return path;
    }

    private Cell visitRandomNeighbour(Cell cell) {
        Cell neighbour = mazeManager.getRandomNeighbour(cell);
        mazeManager.removeVisited(neighbour);
        mazeManager.removeNeighbour(cell, neighbour);
        return neighbour;
    }
}
