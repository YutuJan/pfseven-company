package com.pfseven.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Integer id;
    private BigDecimal total_price;
    private String payment_method;
    private Boolean pending_payment;


}
