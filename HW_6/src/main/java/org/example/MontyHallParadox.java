package org.example;

import java.util.HashMap;
import java.util.Random;

public class MontyHallParadox {
    private static final int TOTAL_GAMES = 1000;
    private static final int DOORS = 3;
    public static void main(String[] args) {
        int wins = 0;
        int losses = 0;

        Random random = new Random();
        for (int i = 0; i < TOTAL_GAMES; i++) {
            int carDoor = random.nextInt(DOORS) + 1;
            int chosenDoor = random.nextInt(DOORS) + 1;
            int openedDoor;
            do {
                openedDoor = random.nextInt(DOORS) + 1;
            } while (openedDoor == carDoor || openedDoor == chosenDoor);

            int finalChoice = 6 - chosenDoor - openedDoor;

            if (finalChoice == carDoor) wins++;
            else losses++;
        }

        HashMap<Integer, String> results = new HashMap<>();
        results.put(1, "Выигрышей: " + wins);
        results.put(2, "Проигрышей: " + losses);

        for (int step: results.keySet()) {
            System.out.println(step + ". " + results.get(step));
        }
    }
}