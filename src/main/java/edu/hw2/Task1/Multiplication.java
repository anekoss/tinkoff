package edu.hw2.Task1;


public record Multiplication(double number1, double number2) implements Expr {
    public Multiplication(Expr expr1, Expr expr2) {
        this(expr1.evaluate(), expr2.evaluate());
    }

    public Multiplication(Expr exp, double number) {
        this(exp.evaluate(), number);
    }

    public Multiplication(double number, Expr exp) {
        this(number, exp.evaluate());
    }

    @Override
    public double evaluate() {
        return number1() * number2();
    }
}
