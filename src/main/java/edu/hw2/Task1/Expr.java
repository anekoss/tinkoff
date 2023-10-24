package edu.hw2.Task1;

sealed public interface Expr permits Constant, Negate, Exponent, Addition, Multiplication {
    double evaluate();
}
