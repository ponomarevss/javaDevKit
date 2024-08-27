package ru.gb.lesson3;

/*
 * 1. Написать класс Калькулятор (необобщенный), который содержит
 * обобщенные статические методы: sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 * Методы должны возвращать результат своей работы.
 *
 * 2. Напишите обобщенный метод compareArrays(),
 * который принимает два массива и возвращает true, если они одинаковые,
 * и false в противном случае.
 * Массивы могут быть любого типа данных,
 * но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам.
 *
 * 3. Напишите обобщенный класс Pair,
 * который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 * */

public class Main {

    public static void main(String[] args) {
        System.out.println("Task 1");
        int a = 9;
        float b = 0;
        System.out.println(Calc.sum(a, b));
        System.out.println(Calc.multiply(a, b));
        System.out.println(Calc.divide(a, b));
        System.out.println(Calc.subtract(a, b));
        System.out.println("------------");

        System.out.println("Task 2");
        Number[] arrA = {1, 2.2, 3.7f};
        Object[] arrB = {1, 2.2, 3.7f};
        Object[] arrC = {5, 8.8, 9.4f};

        System.out.println(ArrComparator.compareArrays(arrA, arrB));
        System.out.println(ArrComparator.compareArrays(arrA, arrC));
        System.out.println(ArrComparator.compareArraysElTypes(arrA, arrB));
        System.out.println(ArrComparator.compareArraysElTypes(arrA, arrC));
        System.out.println("------------");

        System.out.println("Task 3");
        Pair<Boolean, String> response = new Pair<>(false, "Unable to connect to the server. Server is not running.");
        System.out.println(response.getFirst());
        System.out.println(response.getSecond());
    }


}
