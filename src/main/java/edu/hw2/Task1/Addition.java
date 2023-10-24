package edu.hw2.Task1;

public record Addition(double number1, double number2) implements Expr {

    public Addition(Expr expr1, Expr expr2) {
        this(expr1.evaluate(), expr2.evaluate());
    }

    public Addition(Expr exp, double number) {
        this(exp.evaluate(), number);
    }

    public Addition(double number, Expr exp) {
        this(number, exp.evaluate());
    }

    @Override
    public double evaluate() {
        return number1() + number2();
    }

}
