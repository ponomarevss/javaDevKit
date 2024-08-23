package ru.gb.lesson1.server;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    private static final String TITLE = "Chat server";
    private static final String BTN_START = "Start";
    private static final String BTN_STOP = "Stop";

    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 300;
    private static final int WINDOW_POSY = 300;

    private final ServerController serverController;

    private JTextArea taLog;
    private JPanel bottomPanel;
    private JButton btnStart, btnStop;

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(TITLE);
        setResizable(false);

        taLog = new JTextArea();
        serverController = new ServerController(taLog);
        JScrollPane logScrollPane = new JScrollPane(taLog);
        taLog.setEditable(false);
        add(logScrollPane);

        bottomPanel = new JPanel(new GridLayout(1,2));
        btnStart = new JButton(BTN_START);
        btnStop = new JButton(BTN_STOP);
        btnStart.addActionListener(actionEvent -> {
            serverController.btnStartClicked();
        });
        btnStop.addActionListener(actionEvent -> {
            serverController.btnStopClicked();
        });
        bottomPanel.add(btnStart);
        bottomPanel.add(btnStop);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public ServerController getController() {
        return serverController;
    }
}
