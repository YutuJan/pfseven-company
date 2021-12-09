package com.pfseven.eshop.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class Product {
    private @Getter String id;
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

    /**
    public String toString(){
        return "PRODUCT'S ID: " + id + " PRODUCT'S NAME: " + name + " PRODUCT'S COST: " + cost;
    }
     **/
}
