package ru.gb.lesson1.client;

import ru.gb.lesson1.data.User;
import ru.gb.lesson1.server.Handler;
import ru.gb.lesson1.server.Server;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ClientController {
    static final String HINT_IP = "Input IP";
    static final String HINT_PORT = "Input port";
    static final String HINT_LOGIN = "Input login";
    static final String HINT_PASSWORD = "Input password";
    static final String HINT_MESSAGE = "Input message";
    static final String SERVER_IS_NOT_RUNNING = "Unable to connect to the server. Server is not running.";
    static final String EMPTY_FIELD_IP = "Unable to connect to the server. Empty field: IP.";
    static final String EMPTY_FIELD_PORT = "Unable to connect to the server. Empty field: port.";
    static final String EMPTY_FIELD_LOGIN = "Unable to connect to the server. Empty field: login.";
    static final String CONNECTED_TO_THE_SERVER = "You've connected to the server.";
    static final String USER_HAS_SUCCESSFULLY_CONNECTED = "User %s has successfully connected.";
    static final String UNABLE_TO_SEND_MESSAGE_THE_SERVER_IS_NOT_RUNNING = "Unable to send message. The server is not running";
    static final String UNABLE_TO_SEND_MESSAGE_NOT_CONNECTED = "Unable to send message. You are not connected to the server";
    static final String USER_S_DISCONNECTED = "User %s disconnected.";

    private final Server server;
    private final Handler handler;

    public ClientController(Server server, JTextArea textArea) {
        this.server = server;
        this.handler = new Handler(textArea);
    }

    public void btnLoginClicked(String ip, String port, String login, String password, JPanel panel) {
        boolean flag = false;
        if (!server.isServerWorking()) {
            handler.sendMessage(SERVER_IS_NOT_RUNNING);
            return;
        }
        if (ip.equals(HINT_IP)) {
            flag = true;
            handler.sendMessage(EMPTY_FIELD_IP);
        }
        if (port.equals(HINT_PORT)) {
            flag = true;
            handler.sendMessage(EMPTY_FIELD_PORT);
        }
        if (login.equals(HINT_LOGIN)) {
            flag = true;
            handler.sendMessage(EMPTY_FIELD_LOGIN);
        }
        if (flag) {
            return;
        }
        handler.setUser(new User(ip, port, login, password));
        if (server.addClient(handler)) {
            handler.sendMessage(server.readFromHistory());
            handler.sendMessage(CONNECTED_TO_THE_SERVER);
            server.broadcastMessage(String.format(USER_HAS_SUCCESSFULLY_CONNECTED, handler.getUser().getLogin()), handler);
            panel.setVisible(false);
        }
    }

    public void btnSendClicked(String message) {
        if (message.equals(HINT_MESSAGE) || message.isEmpty()) {
            return;
        }
        if (!server.isServerWorking()) {
            handler.sendMessage(UNABLE_TO_SEND_MESSAGE_THE_SERVER_IS_NOT_RUNNING);
            return;
        }
        if (!server.getClients().contains(handler)) {
            handler.sendMessage(UNABLE_TO_SEND_MESSAGE_NOT_CONNECTED);
            return;
        }
        handler.sendMessage(message);
        String broadcastMessage = String.format("%s: %s", handler.getUser().getLogin(), message);
        server.broadcastMessage(broadcastMessage, handler);
        server.saveToHistory(broadcastMessage);
    }

    public void btnCloseClicked() {
        if (server.getClients().contains(handler)) {
            server.broadcastMessage(String.format(USER_S_DISCONNECTED, handler.getUser().getLogin()), handler);
            server.removeClient(handler);
        }
    }

    void initHint(JTextComponent textField, String hint) {
        textField.setText(hint);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(hint);
                }
            }
        });
    }
}
