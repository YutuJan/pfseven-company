package com.pfseven.eshop.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Customer {
    private final String id;
    private final String name;
    private final String category;
    private final Order order = new Order();

    public Customer(String name, String category){
        this.name = name;
        this.category = category;
        this.id = String.valueOf(Objects.hash(this.name));
    }

    public void addOrderItem(Product orderItem){
        order.addOrderItem(orderItem);
    }

    public void removeOrderItem(Product orderItem){
        order.removeOrderItem(orderItem);
    }
}
