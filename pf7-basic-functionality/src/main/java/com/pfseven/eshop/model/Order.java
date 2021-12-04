package com.pfseven.eshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
    private int id;
    private BigDecimal totalCost;
    private ArrayList<OrderItem> orderItems;
    private boolean pending;
}
