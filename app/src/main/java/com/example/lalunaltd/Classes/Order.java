package com.example.lalunaltd.Classes;

import java.util.ArrayList;

public class Order {
    private ArrayList<ItemInOrder> items;
    private String dateTime;

    public Order() {
    }

    public Order(String dateTime) {
        this.items = new ArrayList<>();
        this.dateTime = dateTime;
    }

    public ArrayList<ItemInOrder> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemInOrder> items) {
        this.items = items;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
