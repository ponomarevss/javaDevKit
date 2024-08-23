package ru.gb.lesson1.server;

import ru.gb.lesson1.data.User;

import javax.swing.*;

public class ServerController {
    private static final String SERVER_IS_RUNNING = "The server is running.";
    private static final String SERVER_IS_ALREADY_RUNNING = "The server is already running.";
    private static final String SERVER_IS_STOPPED = "The server is stopped.";
    private static final String SERVER_IS_ALREADY_STOPPED = "The server is already stopped.";
    private static final String IP_ADDRESS = "127.0.0.0";
    private static final String PORT = "8080";
    private static final String LOGIN = "Server";
    private static final String PASSWORD = "";

    private final Server server;
    private final Handler handler;

    public ServerController(JTextArea textArea) {
        server = new Server();
        handler = new Handler(textArea);
        handler.setUser(new User(IP_ADDRESS, PORT, LOGIN, PASSWORD));
    }

    public Server getServer() {
        return server;
    }

    public void btnStartClicked() {
        if (!server.isServerWorking()) {
            server.setServerWorking(true);
            server.addClient(handler);
            handler.sendMessage(SERVER_IS_RUNNING);
            server.broadcastMessage(SERVER_IS_RUNNING, handler);
        } else {
            handler.sendMessage(SERVER_IS_ALREADY_RUNNING);
        }
    }

    public void btnStopClicked() {
        if (server.isServerWorking()) {
            handler.sendMessage(SERVER_IS_STOPPED);
            server.broadcastMessage(SERVER_IS_STOPPED, handler);
            server.removeClient(handler);
            server.setServerWorking(false);

        } else {
            handler.sendMessage(SERVER_IS_ALREADY_STOPPED);
        }
    }
}
