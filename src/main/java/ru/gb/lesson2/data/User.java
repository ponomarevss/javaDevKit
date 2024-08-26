package ru.gb.lesson2.data;

import java.util.Objects;

public class User {
    private String ip;
    private String port;
    private String login = "unauthorized";
    private char[] password;

    public User(String ip, String port, String login, char[] password) {
        this.ip = ip;
        this.port = port;
        this.login = login;
        this.password = password;
    }

    public User() {

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getIp(), user.getIp()) && Objects.equals(getPort(), user.getPort()) && Objects.equals(getLogin(), user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIp(), getPort(), getLogin());
    }

    @Override
    public String toString() {
        return "User{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
