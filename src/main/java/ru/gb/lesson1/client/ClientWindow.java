package ru.gb.lesson1.client;

import ru.gb.lesson1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import static ru.gb.lesson1.client.ClientController.*;

public class ClientWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private static final String TITLE = "Chat client";
    private static final String BTN_LOGIN = "Login";
    private static final String BTN_SEND = "Send";

    private final ClientController controller;

    private JTextArea taLog;
    private JPanel topPanel, bottomPanel;
    private JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    private JPasswordField pfPassword;
    private JButton btnLogin, btnSend;

    public ClientWindow(ServerWindow serverWindow) {

        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.btnCloseClicked();
                super.windowClosing(e);
                dispose();
            }
        });

        taLog = new JTextArea();
        controller = new ClientController(serverWindow.getController().getServer(), taLog);
        taLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taLog);
        add(scrollPane);

        topPanel = new JPanel(new GridLayout(2,3));
        tfIPAddress = new JTextField();
        tfPort = new JTextField();
        tfLogin = new JTextField();
        pfPassword = new JPasswordField();
        controller.initHint(tfIPAddress, HINT_IP);
        controller.initHint(tfPort, HINT_PORT);
        controller.initHint(tfLogin, HINT_LOGIN);
        controller.initHint(pfPassword, HINT_PASSWORD);
        btnLogin = new JButton(BTN_LOGIN);
        btnLogin.addActionListener(actionEvent -> {
            controller.btnLoginClicked(
                    tfIPAddress.getText(),
                    tfPort.getText(),
                    tfLogin.getText(),
                    Arrays.toString(pfPassword.getPassword()),
                    topPanel
            );
        });
        topPanel.add(tfIPAddress);
        topPanel.add(tfPort);
        topPanel.add(tfLogin);
        topPanel.add(pfPassword);
        topPanel.add(btnLogin);
        add(topPanel, BorderLayout.NORTH);

        bottomPanel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        controller.initHint(tfMessage, HINT_MESSAGE);
        tfMessage.addActionListener(actionEvent -> {
            controller.btnSendClicked(tfMessage.getText());
            tfMessage.setText("");
        });
        btnSend = new JButton(BTN_SEND);
        btnSend.addActionListener(actionEvent -> {
            controller.btnSendClicked(tfMessage.getText());
            tfMessage.setText("");
        });
        bottomPanel.add(tfMessage);
        bottomPanel.add(btnSend, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
        taLog.requestFocusInWindow();
    }
}
