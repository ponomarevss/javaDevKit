package ru.gb.lesson2.client;

import ru.gb.lesson2.data.User;
import ru.gb.lesson2.server.Server;

public class Client {
    private static final String SUCCESSFULLY_CONNECTED_TO_THE_SERVER = "You are successfully connected to the server.";
    private static final String UNABLE_TO_CONNECT_TO_THE_SERVER = "Unable to connect to the server.";
    private static final String UNABLE_TO_SEND_MESSAGE_NOT_CONNECTED_TO_THE_SERVER = "Unable to send message. You are not connected to the server.";
    private ClientView view;
    private Server server;
    private User user;
    private boolean connected;
    private boolean disconnected;

    public Client(ClientView view, Server server) {
        this.view = view;
        this.server = server;
        user = new User();
    }

    public void btnCloseClicked() {
        if (connected) {
            server.disconnectClient(this);
        }
    }

    public void btnLoginClicked(String ip, String port, String login, char[] password) {
        user.setIp(ip);
        user.setPort(port);
        user.setLogin(login);
        user.setPassword(password);
        if (server.connectClient(this)) {
            connected = true;
            view.hideHeader();
            if (!disconnected) {
                view.showMessage(server.getHistory());
            }
            view.showMessage(SUCCESSFULLY_CONNECTED_TO_THE_SERVER);
        } else {
            view.showMessage(UNABLE_TO_CONNECT_TO_THE_SERVER);
        }
    }

    public void btnSendClicked(String message) {
        if (message.isEmpty()) {
            return;
        }
        if (connected) {
            view.showMessage(message);
            server.sendMessage(String.format("%s: %s", user.getLogin(), message), this);
        } else {
            view.showMessage(UNABLE_TO_SEND_MESSAGE_NOT_CONNECTED_TO_THE_SERVER);
        }
    }

    public void sendMessage(String message) {
        view.showMessage(message);
    }

    public void disconnect() {
        connected = false;
        disconnected = true;
        view.showHeader();
    }

    public User getUser() {
        return user;
    }
}
