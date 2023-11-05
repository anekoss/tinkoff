package edu.project2.game;

public record Cell(int x, int y, Dictionary type) {
    public enum Dictionary {
        WALL, CELL
    }
}
