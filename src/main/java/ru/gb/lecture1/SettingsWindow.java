package ru.gb.lecture1;

import javax.swing.*;

public class SettingsWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 230;
    public static final int WINDOW_WIDTH = 350;

    JButton btnStart = new JButton("Start new game");
    public SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        btnStart.addActionListener(actionEvent -> {
            gameWindow.startNewGame(0, 3,3,3);
            setVisible(false);
        });
        add(btnStart);
    }
}
