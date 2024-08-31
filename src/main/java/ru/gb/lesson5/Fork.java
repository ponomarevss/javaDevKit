package ru.gb.lesson5;

import java.util.concurrent.atomic.AtomicBoolean;

public class Fork {

    private final String name;
    private final AtomicBoolean available = new AtomicBoolean(true);

    public Fork(String name) {
        this.name = name;
    }

    public AtomicBoolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    @Override
    public String toString() {
        return "Fork{" +
                "name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
