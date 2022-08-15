package com.asaitec.java.tech.interview.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FruitInvoiceItemDTO implements Serializable {

    FruitDTO fruit;
    int quantity;

    public FruitInvoiceItemDTO(FruitDTO fruit, int quantity) {
        this.fruit = fruit;
        this.quantity = quantity;
    }
}
