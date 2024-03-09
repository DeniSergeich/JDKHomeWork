package org.example;

public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public static <T, U> String concatenate(Pair<T, U> pair) {
        return pair.getFirst().toString() + pair.getSecond().toString();
    }
}

