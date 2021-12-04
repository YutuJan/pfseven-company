package com.pfseven.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Integer id;
    private String name;
    private String category;
    private Float price;

}
