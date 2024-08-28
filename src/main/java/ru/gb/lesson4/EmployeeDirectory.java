package ru.gb.lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDirectory {

    /**
     * Класс справочник сотрудников, который содержит внутри
     * коллекцию сотрудников
     * Добавить метод, который ищет сотрудника по стажу (может быть список)
     * Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
     * Добавить метод, который ищет сотрудника по табельному номеру
     * Добавить метод добавления нового сотрудника в справочник
     * */

    private final List<Employee> directory;

    public EmployeeDirectory(ArrayList<Employee> directory) {
        this.directory = directory;
    }

    public List<Employee> getEmployeeListByExp(double exp) {
        return directory.stream()
                .filter(employee -> employee.getWorkExperience() == exp)
                .collect(Collectors.toList());
    }

    public List<String> getPhoneListByName(String name) {
        return directory.stream()
                .filter(employee -> employee.getName().equals(name))
                .map(Employee::getPhone)
                .collect(Collectors.toList());
    }

    public Employee getEmployeeByPersonnelNumber(String pNumber) {
        return directory.stream()
                .filter(employee -> employee.getPersonnelNumber().equals(pNumber))
                .findFirst().orElse(null);
    }

    public boolean addEmployee(Employee emp) {
        Employee exists = directory.stream()
                .filter(employee -> employee.getPersonnelNumber().equals(emp.getPersonnelNumber()))
                .findFirst().orElse(null);
        if (exists == null) {
            directory.add(emp);
            return true;
        } else {
            return false;
        }
    }

    public List<Employee> getDirectory() {
        return directory;
    }
}
