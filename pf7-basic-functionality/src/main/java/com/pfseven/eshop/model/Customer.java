package com.pfseven.eshop.model;

import java.util.Objects;

public class Customer {
    private String id;
    private String name;
    private String category;
    private Order order;

    Customer(String name, String category){
        this.name = name;
        this.category = category;
        this.order = new Order();
        this.id = String.valueOf(Objects.hash(this.name));
    }

    public void addOrderItem(Product orderItem){
        order.addOrderItem(orderItem);
    }

    public void removeOrderItem(Product orderItem){
        order.removeOrderItem(orderItem);
    }

    public String getName(){
        return name;
    }

    public String getID(){
        return id;
    }
}
