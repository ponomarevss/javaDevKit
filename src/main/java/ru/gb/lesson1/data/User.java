package ru.gb.lesson1.data;

import java.util.Objects;

public class User {
    private final String ipAddress;
    private final String port;
    private final String login;
    private final String password;

    public User(String ipAddress, String port, String login, String password) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.login = login;
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getPort() {
        return port;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port='" + port + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getIpAddress(), user.getIpAddress())
                && Objects.equals(getPort(), user.getPort())
                && Objects.equals(getLogin(), user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIpAddress(), getPort(), getLogin(), getPassword());
    }
}
