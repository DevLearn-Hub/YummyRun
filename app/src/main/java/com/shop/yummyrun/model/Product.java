package com.shop.yummyrun.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;
    private double price;
    private int imageResId;
    private String category;

    public Product(String name, String description, double price, int imageResId, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getCategory() {
        return category; // Метод для получения категории
    }
}
