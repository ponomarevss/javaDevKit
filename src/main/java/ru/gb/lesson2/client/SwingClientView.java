package ru.gb.lesson2.client;

import ru.gb.lesson2.server.ServerView;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingClientView extends JFrame implements ClientView {
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POS_X = 800;
    private static final int WINDOW_POS_Y = 300;
    private static final String TITLE = "Chat client";
    private static final String BTN_LOGIN = "Login";
    private static final String BTN_SEND = "Send";
    private static final String HINT_IP = "IP";
    private static final String HINT_PORT = "Port";
    private static final String HINT_LOGIN = "Login";
    private static final String HINT_PASSWORD = "Password";
    private static final String HINT_MESSAGE = "Message";

    private final Client client;

    private JTextArea taLog;
    private JPanel topPanel, bottomPanel;
    private JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    private JPasswordField pfPassword;
    private JButton btnLogin, btnSend;

    public SwingClientView(ServerView serverView) {
        this.client = new Client(this, serverView.getServer());

        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.btnCloseClicked();
                super.windowClosing(e);
                dispose();
            }
        });

        initHeader();
        initLogPanel();
        initFooter();

        setVisible(true);
        taLog.requestFocusInWindow();
    }

    @Override
    public void showMessage(String message) {
        taLog.append(message + "\n");
    }

    @Override
    public void showHeader() {
        topPanel.setVisible(true);
    }

    @Override
    public void hideHeader() {
        topPanel.setVisible(false);
    }

    private void initHeader() {
        topPanel = new JPanel(new GridLayout(2,3));
        tfIPAddress = new JTextField();
        tfPort = new JTextField();
        tfLogin = new JTextField();
        pfPassword = new JPasswordField();
        initHint(tfIPAddress, HINT_IP);
        initHint(tfPort, HINT_PORT);
        initHint(tfLogin, HINT_LOGIN);
        initHint(pfPassword, HINT_PASSWORD);
        btnLogin = new JButton(BTN_LOGIN);
        btnLogin.addActionListener(actionEvent -> client.btnLoginClicked(
                tfIPAddress.getText(),
                tfPort.getText(),
                tfLogin.getText(),
                pfPassword.getPassword()
        ));
        topPanel.add(tfIPAddress);
        topPanel.add(tfPort);
        topPanel.add(new JPanel());
        topPanel.add(tfLogin);
        topPanel.add(pfPassword);
        topPanel.add(btnLogin);
        add(topPanel, BorderLayout.NORTH);

    }

    private void initLogPanel() {
        taLog = new JTextArea();
        taLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taLog);
        add(scrollPane);
    }

    private void initFooter() {
        bottomPanel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addActionListener(actionEvent -> {
            client.btnSendClicked(tfMessage.getText());
            tfMessage.setText("");
        });
        btnSend = new JButton(BTN_SEND);
        btnSend.addActionListener(actionEvent -> {
            client.btnSendClicked(tfMessage.getText());
            tfMessage.setText("");
        });
        bottomPanel.add(tfMessage);
        bottomPanel.add(btnSend, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    void initHint(JTextComponent textField, String hint) {
        setHint(textField, hint);
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
                    setHint(textField, hint);
                }
            }
        });
    }

    private static void setHint(JTextComponent textField, String hint) {
        textField.setForeground(Color.GRAY);
        textField.setText(hint);
    }
}
