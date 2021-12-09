package com.pfseven.eshop.model;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Order {
    private String id;
    private ArrayList<Product> orderItems;

    public Order(){
        id = String.valueOf(this.hashCode());
        orderItems = new ArrayList<>();
    }

    public void addOrderItem(Product orderItem){
        orderItems.add(orderItem);
    }

    public void removeOrderItem(Product orderItem){
        orderItems.remove(orderItem);
    }
}
