package org.example;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread{
    private String name;
    private int leftFork;
    private int rightFork;
    private int countEat;
    private Random random;
    private CountDownLatch countDownLatch;
    private Table table;

    public Philosopher(String name, Table table, int leftFork, int rightFork, CountDownLatch countDownLatch) {
        this.table = table;
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.countEat = 0;
        random = new Random();
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run(){
        while (countEat < 3){
            try {
                thinking();
                eating();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " наелся.");
        countDownLatch.countDown();
    }

    private void eating() throws InterruptedException {
        if (table.tryGetForks(leftFork,rightFork)){
            System.out.println(name + " ест, используя вилки: " + leftFork + " и " + rightFork);
            sleep(random.nextLong(2000, 4000));
            table.putForks(leftFork, rightFork);
            System.out.println(name + " поел, можно и подумать," +
                    " не забыв вернуть вилки " + leftFork + " и " + rightFork);
            countEat++;
        }
    }

    private void thinking() throws InterruptedException {
        sleep(random.nextLong(50, 1000));
    }
}
