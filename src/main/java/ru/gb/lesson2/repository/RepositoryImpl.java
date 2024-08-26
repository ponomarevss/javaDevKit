package ru.gb.lesson2.repository;

import java.io.*;

public class RepositoryImpl implements Repository {
    private static final String FILE_NAME = "src/main/java/ru/gb/lesson2/repository/History.txt";

    @Override
    public void saveHistory(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public String readHistory() {
        StringBuilder history = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return history.toString();
    }
}
