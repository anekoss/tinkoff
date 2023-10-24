package edu.hw2.Task1;



public record Constant(double number) implements Expr {
    public Constant(Expr expr) {
        this(expr.evaluate());
    }

    @Override
    public double evaluate() {
        return number;
    }

}
