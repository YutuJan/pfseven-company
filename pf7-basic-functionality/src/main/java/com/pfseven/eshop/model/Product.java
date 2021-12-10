package com.pfseven.eshop.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class Product {
    private final @Getter
    String id;
    private final String name;
    private final @Getter
    double cost;

    public Product(String name, String cost) {
        this.name = name;
        this.cost = Double.parseDouble(cost);
        id = String.valueOf(Objects.hash(this.name, this.cost));
    }
}
