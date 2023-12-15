package edu.hw10.Task2;

class CalculatorImlp implements Calculator {

    @Override
    public int plus(int number1, int number2) {
        return number1 + number2;
    }

    public int multiply(int number1, int number2) {
        return number1 * number2;
    }

    @Override
    public int minus(int number1, int number2) {
        return number1 - number2;
    }
}

interface Calculator {

    @Cache(persist = true)
    int plus(int number1, int number2);

    int multiply(int number1, int number2);

    @Cache
    int minus(int number1, int number2);
}
