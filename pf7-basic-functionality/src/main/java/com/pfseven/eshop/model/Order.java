package com.pfseven.eshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;

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

    public String getID(){
        return id;
    }

    public ArrayList<Product> getOderItems(){
        return orderItems;
    }

    /**
    public Product getProduct(String productID){
        for (Product product: orderItems){
            if (product.getID().equals(productID)){
                return product;
            }
        }
        return null;
    }
     **/
}
