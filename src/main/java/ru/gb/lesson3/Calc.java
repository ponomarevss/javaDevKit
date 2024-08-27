package ru.gb.lesson3;

/*
 * 1. Написать класс Калькулятор (необобщенный), который содержит
 * обобщенные статические методы: sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 * Методы должны возвращать результат своей работы.
 * */

public class Calc {
    public static <N1 extends Number, N2 extends Number> double sum(N1 a, N2 b) {
        return a.doubleValue() + b.doubleValue();
    }

    public static <N1 extends Number, N2 extends Number> double multiply(N1 a, N2 b) {
        return a.doubleValue() * b.doubleValue();
    }
    public static <N1 extends Number, N2 extends Number> double divide(N1 a, N2 b) {
        return a.doubleValue() / b.doubleValue();
    }

    public static <N1 extends Number, N2 extends Number> double subtract(N1 a, N2 b) {
        return a.doubleValue() - b.doubleValue();
    }
}
