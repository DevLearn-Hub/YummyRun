package com.shop.yummyrun.model;

public class Order {
    private String orderId;
    private String orderDetails;
    private String status;

    public Order(String orderId, String orderDetails, String status) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
