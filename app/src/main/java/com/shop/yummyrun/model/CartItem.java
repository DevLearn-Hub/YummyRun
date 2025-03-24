package com.shop.yummyrun.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String productName;
    private int productImageResId;
    private double productPrice;
    private int quantity;

    public CartItem(String productName, int productImageResId, double productPrice, int quantity) {
        this.productName = productName;
        this.productImageResId = productImageResId;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImageResId() {
        return productImageResId;
    }

    public void setProductImageResId(int productImageResId) {
        this.productImageResId = productImageResId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
