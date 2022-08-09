package com.asaitec.java.tech.interview.dto;

import lombok.Data;

@Data
public class FruitDTO {

    /* The name of the fruit */
    private String name;

    /* The description of the fruit */
    private String description;

    /* The fruit price by unit */
    private double unitPrice;

    public FruitDTO(String name) {
        this.name = name;
    }

    public FruitDTO(String name, double unitPrice, String description) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
    }

}
