package ru.gb.lesson5;

import java.util.Random;

public class Philosopher extends Thread{
    private static final int MAX_EAT_COUNT = 3;
    private final String name;
    private Table table;
    private Fork leftFork;
    private Fork rightFork;
    private int eatCount;

    public Philosopher(String name, Fork leftFork, Fork rightFork, Table table) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.table = table;
        eatCount = 0;
    }

    @Override
    public void run() {
        while (eatCount < MAX_EAT_COUNT) {
            eat();
            think();
        }
        System.out.printf("%S is fed enough%n", name);
    }

    private void eat() {
        boolean forksTaken;
        do {
            forksTaken = table.takeForks(leftFork, rightFork);
        } while (!forksTaken);
        System.out.printf("%S takes food %s time%n", name, ++eatCount);
        try {
            Thread.sleep(500 + new Random().nextInt(500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        leftFork.setAvailable(true);
        rightFork.setAvailable(true);
        System.out.printf("%S puts forks and starts to ponder%n", name);
    }

    private void think(){
        try {
            Thread.sleep(500 + new Random().nextInt(500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
