package ru.gb.lesson2.server;

import javax.swing.*;
import java.awt.*;

public class SwingServerView extends JFrame implements ServerView {
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POS_X = 300;
    private static final int WINDOW_POS_Y = 300;
    private static final String TITLE = "Chat server";
    private static final String BTN_START = "Start";
    private static final String BTN_STOP = "Stop";

    private Server server;

    private JTextArea taLog;
    private JPanel bottomPanel;
    private JButton btnStart, btnStop;

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public void showMessage(String message) {
        taLog.append(message + "\n");
    }

    public SwingServerView() {
        server = new Server(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(TITLE);
        setResizable(false);

        initLogPanel();
        initFooter();

        setVisible(true);
    }

    private void initLogPanel() {
        taLog = new JTextArea();
        JScrollPane logScrollPane = new JScrollPane(taLog);
        taLog.setEditable(false);
        add(logScrollPane);
    }

    private void initFooter() {
        bottomPanel = new JPanel(new GridLayout(1,2));
        btnStart = new JButton(BTN_START);
        btnStop = new JButton(BTN_STOP);
        btnStart.addActionListener(actionEvent -> server.btnStartClicked());
        btnStop.addActionListener(actionEvent -> server.btnStopClicked());
        bottomPanel.add(btnStart);
        bottomPanel.add(btnStop);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
