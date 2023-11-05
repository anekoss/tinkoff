package edu.project2;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;

import edu.project2.generators.DFSMazeGenerator;
import edu.project2.generators.KruskalMazeGenerator;
import edu.project2.generators.PrimMazeGenerator;
import edu.project2.solver.BFSUtil;
import edu.project2.solver.BSFSolver;
import edu.project2.solver.DSFSolver;
import java.util.List;
import static edu.project2.game.Cell.Dictionary.WALL;

public class MazeGenerator {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_BACKGROUND
        = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND
        = "\u001B[43m";

    public static final String ANSI_RED_BACKGROUND
        = "\u001B[41m";

    public static void main(String[] args) throws BadFieldMazeException {
        int height = 51;
        int width = 53;
        KruskalMazeGenerator kruskal1 = new KruskalMazeGenerator();
        Maze maze1 = kruskal1.generateMaze(height, width);
        Cell[][] maze = maze1.getGrid();

//        DepthSearchMazeGenerator depthSearchMazeGenerator = new DepthSearchMazeGenerator(height, width);
//        Maze maze1 = depthSearchMazeGenerator.generateMaze();
//        BSFSolver solver = new BSFSolver();
//        List<Cell> stack =
//            solver.solve(maze1,new Coordinate(1, 1), new Coordinate(7, 7));
//        System.out.println(stack.size());

        height = maze1.getHeight();
        width = maze1.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
//                if (maze[i][j].equals(new Cell(1, 1, Cell.Dictionary.CELL)) ||
//                    maze[i][j].equals(new Cell(5, 5, Cell.Dictionary.CELL))) {
//                    System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_RESET);
//                } else if (stack.contains(maze[i][j])) {
//                    System.out.print(ANSI_RED_BACKGROUND + "  " + ANSI_RESET);
//                } else
                if (maze[i][j].type() == WALL) {
                    System.out.print(ANSI_WHITE_BACKGROUND + "  " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RESET + "  " + ANSI_RESET);
                }

            }
            System.out.println();
        }
    }

}
