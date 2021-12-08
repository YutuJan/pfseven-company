package com.pfseven.eshop.model;

import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private double cost;

    public Product(String name, double cost){
        this.name = name;
        this.cost = cost;
        id = String.valueOf(Objects.hash(this.name, this.cost));
    }
}
