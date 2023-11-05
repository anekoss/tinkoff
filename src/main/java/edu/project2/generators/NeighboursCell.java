package edu.project2.generators;

import edu.project2.game.Cell;
import java.util.Objects;
import java.util.Random;

public class NeighboursCell {
    private static final int MAX_WEIGHT = 100;
    private static final Random RANDOM = new Random();
    private final Cell cell1;
    private final Cell cell2;
    private final int weight;

    public NeighboursCell(Cell cell1, Cell cell2) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.weight = RANDOM.nextInt(MAX_WEIGHT);
    }

    public int getWeight() {
        return weight;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NeighboursCell neighboursCell = (NeighboursCell) o;
        return cell1.equals(neighboursCell.cell1) && cell2.equals(neighboursCell.cell2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell1, cell2);
    }

    public Cell getCell1() {
        return cell1;
    }

    public Cell getCell2() {
        return cell2;
    }

}
