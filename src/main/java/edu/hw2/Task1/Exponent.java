package edu.hw2.Task1;


public record Exponent(double number1, double number2) implements Expr {
    public Exponent(Expr expr1, Expr expr2) {
        this(expr1.evaluate(), expr2.evaluate());
    }

    public Exponent(Expr exp, double number) {
        this(exp.evaluate(), number);
    }

    public Exponent(double number, Expr exp) {
        this(number, exp.evaluate());
    }

    @Override

    public double evaluate() {
        return Math.pow(number1(), number2());
    }
}
