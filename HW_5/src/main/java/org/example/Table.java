package org.example;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.example.PhilosopherName.randomName;

public class Table extends Thread{
    private final int PHILOSOPHER_COUNT = 5;
    private Fork[] forks;
    private Philosopher[] philosophers;
    private CountDownLatch countDownLatch;

    public Table(){
        forks = new  Fork[PHILOSOPHER_COUNT];
        philosophers = new Philosopher[PHILOSOPHER_COUNT];
        countDownLatch = new CountDownLatch(PHILOSOPHER_COUNT);
        init();
    }

    @Override
    public void run(){
        System.out.println("Заседание объявляется открытым!");
        try {
            thinkingProcess();
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Все наелись!");
    }

    public synchronized boolean tryGetForks(int leftFork, int rightFork){
        if (!forks[leftFork].isUsing() && !forks[rightFork].isUsing()){
            forks[leftFork].setUsing(true);
            forks[rightFork].setUsing(true);
            return true;
        }
        return false;
    }

    public void putForks(int leftFork, int rightFork){
        forks[leftFork].setUsing(false);
        forks[rightFork].setUsing(false);
    }

    private void init(){
        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            forks[i] = new Fork();
        }
        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            philosophers[i] = new Philosopher(randomName().toString(),
                    this, i, (i + 1) % PHILOSOPHER_COUNT, countDownLatch);
        }
    }

    private void thinkingProcess(){
        for (Philosopher philosopher: philosophers) {
            philosopher.start();
        }
    }
}
