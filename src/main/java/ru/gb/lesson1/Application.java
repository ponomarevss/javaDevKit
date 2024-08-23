package ru.gb.lesson1;

import ru.gb.lesson1.client.ClientWindow;
import ru.gb.lesson1.server.ServerWindow;

public class Application {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow);
        new ClientWindow(serverWindow);
    }
}
