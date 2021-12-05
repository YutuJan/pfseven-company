package com.pfseven.eshop.model;

import java.util.HashMap;

public class CustomerService {
    private HashMap<String, Customer> customersWithPendingOrders;
    private HashMap<String, Product> bunchOfProducts;

    public CustomerService() {
        customersWithPendingOrders = new HashMap<String, Customer>();
        bunchOfProducts = new HashMap<String, Product>();
    }

    public void payOrder(String customerID, String paymentMethod){

    }

    public void addOrderItem(String customerID, String productID){

    }

    public void removeOrderItem(String customerID, String productID){

    }

    private double computeDiscount(String customerCategory, String paymentMethod, Product boughtProduct){
        return 0;//TODO replace with the actual func
    }
}
