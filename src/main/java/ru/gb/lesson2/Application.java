package ru.gb.lesson2;

import ru.gb.lesson2.client.SwingClientView;
import ru.gb.lesson2.server.ServerView;
import ru.gb.lesson2.server.SwingServerView;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServerView serverView = new SwingServerView();
            new SwingClientView(serverView);
            new SwingClientView(serverView);
        });
    }
}
