package com.pfseven.eshop.model;

public class Customer {
    private String id;
    private String name;
    private String category;
    private Order order;

    public void addOrderItem(Product orderItem){
        order.addOrderItem(orderItem);
    }

    public void removeOrderItem(Product orderItem){
        order.removeOrderItem(orderItem);
    }
}
