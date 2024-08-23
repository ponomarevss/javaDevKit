package ru.gb.lesson1.server;

import ru.gb.lesson1.data.User;

import javax.swing.*;
import java.util.Objects;

public class Handler {
    private final JTextArea textArea;
    private User user = null;

    public Handler(JTextArea textArea) {
        this.textArea = textArea;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void sendMessage(String message) {
        textArea.append(message + "\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Handler)) return false;
        Handler handler = (Handler) o;
        return Objects.equals(getUser(), handler.getUser());
    }

    @Override
    public String toString() {
        return "Handler{" +
                "textArea=" + textArea +
                ", user=" + user +
                '}';
    }
}