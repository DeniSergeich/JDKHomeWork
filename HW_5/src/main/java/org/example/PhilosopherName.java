package org.example;

import java.util.Random;

public enum PhilosopherName {
    Аристотель, Платон, Сократ, Декарт, Спиноза, Локк, Лейбниц, Беркли, Гоббс, Макиавелли;

    private static final Random NAME = new Random();

    public static PhilosopherName randomName(){
        PhilosopherName[] philosopherNames = values();
        return philosopherNames[NAME.nextInt(philosopherNames.length)];
    }

}
