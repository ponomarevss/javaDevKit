package ru.gb.lesson5;

import java.util.Arrays;
import java.util.Random;

public class Philosopher extends Thread{
    private static final int MAX_EAT_COUNT = 3;
    private final String name;
    private final Fork[] forks;
    private int eatCount;
    private boolean isFed;

    public Philosopher(String name, Fork[] forks) {
        this.name = name;
        this.forks = forks;
        eatCount = 0;
        isFed = false;
    }

    @Override
    public void run() {
        while (eatCount < MAX_EAT_COUNT) {
            eat();
            think();
        }
    }

    private void eat() {
//        тянемся к вилкам
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//      если вилки доступны, берем и едим
        if (areForksAvailable()) {
            setForksAvailability(false);
            eatCount++;
            System.out.printf("%s takes food %s time%n", name, eatCount);
            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isFed = true;
            System.out.printf("%s finishes to take food %s time%n", name, eatCount);
            setForksAvailability(true);
        }
    }

    private void think() {
        if (isFed) {
            System.out.printf("%s starts to ponder%n", name);
            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%s finishes to ponder%n", name);
            isFed = false;
        }
    }

    private boolean areForksAvailable(){
        for (Fork fork : forks) {
            if (!fork.isAvailable().get()) {
                return false;
            }
        }
        return true;
    }

    private void setForksAvailability(boolean available) {
        for (Fork fork : forks) {
            fork.setAvailable(available);
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "name='" + name + '\'' +
                ", forks=" + Arrays.toString(forks) +
                '}';
    }
}
