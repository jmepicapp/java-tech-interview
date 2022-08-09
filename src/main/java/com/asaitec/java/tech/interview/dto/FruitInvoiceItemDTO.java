package com.asaitec.java.tech.interview.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FruitInvoiceItemDTO implements Serializable {

    String fruitName;
    double unitPrize;
    int quantity;

    public FruitInvoiceItemDTO(String fruitName, double unitPrize){
        this.fruitName = fruitName;
        this.unitPrize = unitPrize;
    }

    public FruitInvoiceItemDTO(String fruitName, int quantity) {
        this.fruitName = fruitName;
        this.quantity = quantity;
    }
}
