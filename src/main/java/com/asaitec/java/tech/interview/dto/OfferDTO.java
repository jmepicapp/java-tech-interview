package com.asaitec.java.tech.interview.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OfferDTO implements Serializable {

    public double discount;
    public List<FruitDTO> fruitList;
}
