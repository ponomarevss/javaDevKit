package ru.gb.lesson2.server;

import ru.gb.lesson2.client.Client;
import ru.gb.lesson2.repository.Repository;
import ru.gb.lesson2.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final String SERVER_IS_RUNNING = "The server is running.";
    private static final String SERVER_IS_ALREADY_RUNNING = "The server is already running.";
    private static final String SERVER_IS_STOPPED = "The server is stopped.";
    private static final String SERVER_IS_ALREADY_STOPPED = "The server is already stopped.";
    public static final String USER_CONNECTED = "User %s connected.";
    public static final String USER_DISCONNECTED = "User %s disconnected.";

    private boolean isRunning;
    private final ServerView view;
    private final List<Client> clients;
    private final Repository repository;

    public Server(ServerView view) {
        this.view = view;
        clients = new ArrayList<>();
        repository = new RepositoryImpl();
    }

    void btnStartClicked() {
        if (!isRunning) {
            isRunning = true;
            view.showMessage(SERVER_IS_RUNNING);
        } else {
            view.showMessage(SERVER_IS_ALREADY_RUNNING);
        }
    }

    void btnStopClicked() {
        if (isRunning) {
            isRunning = false;
            view.showMessage(SERVER_IS_STOPPED);
            broadcast(SERVER_IS_STOPPED, null);
            for (Client client : clients) {
                client.disconnect();
            }
            clients.clear();
        } else {
            view.showMessage(SERVER_IS_ALREADY_STOPPED);
        }
    }

    public boolean connectClient(Client client) {
        if (isRunning) {
            clients.add(client);
            String connectClientMessage = String.format(USER_CONNECTED, client.getUser().getLogin());
            broadcast(connectClientMessage, client);
            return true;
        } else {
            return false;
        }
    }

    public void disconnectClient(Client client) {
        if (isRunning) {
            clients.remove(client);
            String disconnectClientMessage = String.format(USER_DISCONNECTED, client.getUser().getLogin());
            broadcast(disconnectClientMessage, client);
        }
    }

    public String getHistory() {
        return repository.readHistory();
    }

    public void sendMessage(String message, Client sender) {
        if (isRunning) {
            broadcast(message, sender);
            repository.saveHistory(message);
        }
    }

    private void broadcast(String message, Client sender) {
        view.showMessage(message);
        for (Client client : clients) {
            if (!client.equals(sender)) {
                client.sendMessage(message);
            }
        }
    }
}
