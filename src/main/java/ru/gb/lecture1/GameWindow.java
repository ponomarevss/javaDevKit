package ru.gb.lecture1;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 800;
    public static final int WINDOW_POSY = 300;

    JButton btnStart = new JButton("New Game");
    JButton btnExit = new JButton("Exit");

    Map map;
    SettingsWindow settings;

    GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe");
        setResizable(false);

        map = new Map();
        settings = new SettingsWindow(this);

        btnExit.addActionListener(actionEvent -> System.exit(0));
        btnStart.addActionListener(actionEvent -> settings.setVisible(true));

        JPanel panBottom = new JPanel(new GridLayout(1,2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        map.drawNewMap(mode, fSzX, fSzY, wLen);
    }
}
