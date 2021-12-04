package com.pfseven.eshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
    private int id;
    private float totalCost;
    private ArrayList<Product> orderItems;
    private boolean pending;
}
