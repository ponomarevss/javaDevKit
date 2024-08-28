package ru.gb.lesson4;

public class Employee {
    private String personnelNumber;
    private String phone;
    private String name;
    private double workExperience;

    public Employee(String personnelNumber, String phone, String name, double workExperience) {
        this.personnelNumber = personnelNumber;
        this.phone = phone;
        this.name = name;
        this.workExperience = workExperience;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(double workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "personnelNumber='" + personnelNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", workExperience=" + workExperience +
                '}';
    }
}
