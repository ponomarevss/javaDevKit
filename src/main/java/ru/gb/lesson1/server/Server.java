package ru.gb.lesson1.server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final String FILE_NAME = "src/main/java/ru/gb/lesson1/server/History.txt";
    private boolean isServerWorking = false;
    private final List<Handler> clients = new ArrayList<>();

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void setServerWorking(boolean serverWorking) {
        isServerWorking = serverWorking;
    }

    public List<Handler> getClients() {
        return clients;
    }

    public boolean addClient(Handler client) {
        if (!clients.contains(client)) {
            clients.add(client);
            return true;
        }
        return false;
    }

    public void removeClient(Handler client) {
        clients.remove(client);
    }

    public void broadcastMessage(String message, Handler sender) {
        for (Handler client : clients) {
            if (!client.equals(sender)) {
                client.sendMessage(message);
            }
        }
    }

    public void saveToHistory(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String readFromHistory() {
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
