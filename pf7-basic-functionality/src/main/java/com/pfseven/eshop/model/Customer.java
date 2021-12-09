package com.pfseven.eshop.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Customer {
    private String id;
    private String name;
    private String category;
    private Order order;

    public Customer(String name, String category){
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

    /**
    public String toString(){
        return "CUSTOMER'S ID: " + id + " CUSTOMER'S NAME: " + name + " CUSTOMER'S CATEGORY: " + category;
    }
     **/
}
