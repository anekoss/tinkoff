package edu.hw2.Task1;

public record Negate(double number) implements Expr {
    public Negate(Expr expr1) {
        this(expr1.evaluate());
    }

    @Override
    public double evaluate() {
        return -number();
    }
}
