package com.pfseven.eshop.model;

import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private double cost;

    public Product(String name, String cost){
        this.name = name;
        this.cost = Double.parseDouble(cost);
        id = String.valueOf(Objects.hash(this.name, this.cost));
    }

    public double getCost(){
        return cost;
    }

    public String getID(){
        return id;
    }

    public String toString(){
        return "product's ID: " + id + " product's name: " + name + " product's cost: " + cost;
    }
}
