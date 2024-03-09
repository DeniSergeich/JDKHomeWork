package org.example;

import static org.example.Pair.concatenate;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(10, "hello");
        System.out.println(concatenate(pair));
    }
}