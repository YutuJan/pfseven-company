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

    //For temporary use only
    Customer(String id, String name, String category){
        this.id = id;
        this.name = name;
        this.category = category;
        this.order = new Order();
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

    public String getCategory() {
        return category;
    }

    public Order getOrder(){
        return order;
    }

    public String toString(){
        return "customer's ID: " + id + " customer's name: " + name + " customer's category: " + category;
    }
}
