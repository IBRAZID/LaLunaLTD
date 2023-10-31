package com.example.lalunaltd;

public class Product {
    private String name;
    private String description;
    private String Catagory;

    public Product(String name, String description, String catagory) {
        this.name = name;
        this.description = description;
        Catagory = catagory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", Catagory='" + Catagory + '\'' +
                '}';
    }
}
