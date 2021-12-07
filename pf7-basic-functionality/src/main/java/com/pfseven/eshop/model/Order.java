package com.pfseven.eshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
    private String id;
    private double totalCost;
    private ArrayList<Product> orderItems;

    public void addOrderItem(Product orderItem){
        orderItems.add(orderItem);
    }

    public void removeOrderItem(Product orderItem){
        orderItems.remove(orderItem);
    }
}
