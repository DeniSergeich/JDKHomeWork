package org.example;

class Database implements DatabaseOperations {
    @Override
    public void saveData(String data) {
        System.out.println("Saving data: " + data);
    }

    @Override
    public void deleteData(String data) {
        System.out.println("Deleting data: " + data);
    }

    @Override
    public String getData() {
        return "Retrieving data...";
    }
}
