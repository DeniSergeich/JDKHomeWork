package org.example;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();

        String dataToSave = "Какие-то данные";
        database.saveData(dataToSave);

        String dataToDelete = "Какие-то данные";
        database.deleteData(dataToDelete);

        System.out.println(database.getData());
    }
}