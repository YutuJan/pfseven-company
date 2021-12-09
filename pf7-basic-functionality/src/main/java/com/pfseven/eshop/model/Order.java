package com.pfseven.eshop.model;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Order {
    private final String id;
    private final ArrayList<Product> orderItems = new ArrayList<>();

    public Order(){
        id = String.valueOf(this.hashCode());
    }

    public void addOrderItem(Product orderItem){
        orderItems.add(orderItem);
    }

    public void removeOrderItem(Product orderItem){
        orderItems.remove(orderItem);
    }
}
