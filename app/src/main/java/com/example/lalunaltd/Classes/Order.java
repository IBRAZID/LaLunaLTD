package com.example.lalunaltd.Classes;

import java.util.ArrayList;

public class Order {
    private ArrayList<ItemInOrder> items;
    private String dateTime;

    public Order(ArrayList<ItemInOrder> items, String dateTime) {
        this.items = items;
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
}
