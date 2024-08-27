package ru.gb.lesson3;

/*
 * 2. Напишите обобщенный метод compareArrays(),
 * который принимает два массива и возвращает true, если они одинаковые,
 * и false в противном случае.
 * Массивы могут быть любого типа данных,
 * но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам.
 * */

public class ArrComparator {
    /*
    * Из задания не понятно, нужно ли мне проверять в методе только попарное сходство классов элементов
    * или полностью их тождественность
    * */
    public static <T1, T2> boolean compareArrays(T1[] arrA, T2[] arrB) {
        if (arrA.length != arrB.length) {
            return false;
        }
        for (int i = 0; i < arrA.length; i++) {
            if (!arrA[i].equals(arrB[i])) {
                return false;
            }
        }
        return true;
    }
    public static <T1, T2> boolean compareArraysElTypes(T1[] arrA, T2[] arrB) {
        if (arrA.length != arrB.length) {
            return false;
        }
        for (int i = 0; i < arrA.length; i++) {
            if (!arrA[i].getClass().equals(arrB[i].getClass())) {
                return false;
            }
        }
        return true;
    }
}
