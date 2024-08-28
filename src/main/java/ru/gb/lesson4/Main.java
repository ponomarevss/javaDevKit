package ru.gb.lesson4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDirectory employeeDirectory = getEmployeeDirectory();
//      вывод созданного справочника
        System.out.printf("getDirectory(): %s%n", employeeDirectory.getDirectory());

//      вывод результата работы метода, который ищет сотрудника по стажу (может быть список)
        System.out.printf("getEmployeeListByExp(2.5): %s%n", employeeDirectory.getEmployeeListByExp(2.5));

//      вывод результата работы метода, который возвращает номер телефона сотрудника по имени (может быть список)
        System.out.printf("getPhoneListByName(\"Golf\"): %s%n", employeeDirectory.getPhoneListByName("Golf"));

//      вывод результата работы метода, который ищет сотрудника по табельному номеру
        System.out.printf("getEmployeeByPersonnelNumber(\"2311\"): %s%n",
                employeeDirectory.getEmployeeByPersonnelNumber("2311"));

//      вывод результата работы метода добавления нового сотрудника в справочник
//      1. в случае попытки добавления существуюшего сотрудника
        System.out.printf("addEmployee(new Employee(\"2309\", \"+89174052299\", \"Alpha\", 2.5)): %s%n",
                employeeDirectory.addEmployee(
                        new Employee("2309", "+89174052299", "Alpha", 2.5)));

//      2. в случае попытки добавления нового сотрудника
        System.out.printf("addEmployee(new Employee(\"2322\", \"+89174053344\", \"Foxtrot\", 2.5)): %s%n",
                employeeDirectory.addEmployee(
                        new Employee("2322", "+89174053344", "Foxtrot", 2.5)));
    }

    private static EmployeeDirectory getEmployeeDirectory() {
        return new EmployeeDirectory(new ArrayList<>(List.of(
                new Employee("2309", "+89174052299", "Alpha", 2.5),
                new Employee("2310", "+89174052200", "Bravo", 3.0),
                new Employee("2311", "+89174054201", "Golf", 2.5),
                new Employee("2314", "+89174052824", "Golf", 5.0)
        )));
    }
}
