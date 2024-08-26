package ru.gb.lesson2.repository;

public interface Repository {
    void saveHistory(String message);
    String readHistory();
}
