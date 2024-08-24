package com.example.itsadeal;

public class Order {
    private String name;
    private String amount;
    private String deliveryTime;

    public Order() {
        // Default constructor required for Firebase
    }

    public Order(String name, String amount, String deliveryTime) {
        this.name = name;
        this.amount = amount;
        this.deliveryTime = deliveryTime;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }
}

