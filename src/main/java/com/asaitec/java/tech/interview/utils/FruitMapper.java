package com.asaitec.java.tech.interview.utils;

import com.asaitec.java.tech.interview.dto.FruitDTO;
import com.asaitec.java.tech.interview.model.FruitEntity;

public class FruitMapper {
    public FruitEntity FruitDTOToFruitEntity(FruitDTO fruitDTO) {
        FruitEntity fruitEntity = new FruitEntity();
        fruitEntity.setName(fruitDTO.getName());
        fruitEntity.setDescription(fruitDTO.getDescription());
        fruitEntity.setUnitPrice(fruitDTO.getUnitPrice());
        return fruitEntity;
    }

    public FruitDTO FruitDTOToFruitEntity(FruitEntity fruitEntity){
        FruitDTO fruitDTO = new FruitDTO(
                fruitEntity.getName(),
                fruitEntity.getUnitPrice(),
                fruitEntity.getDescription()
        );
        return fruitDTO;
    }
}
