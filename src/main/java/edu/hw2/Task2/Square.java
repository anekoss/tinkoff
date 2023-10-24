package edu.hw2.Task2;

public class Square extends Rectangle {
    public Square() {
    }

    public Square(int width, int height) {
        super(width, height);

    }

    public Square(int width) {
        super(width, width);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }

}
