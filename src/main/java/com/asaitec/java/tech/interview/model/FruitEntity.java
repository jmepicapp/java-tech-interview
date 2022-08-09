package com.asaitec.java.tech.interview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "FRUITS")

@Getter
@Setter
public class FruitEntity implements Serializable {

    /* The id */
    @Id
    private Long id;

    /* The name of the fruit */
    private String name;

    /* The description of the fruit */
    private String description;

    /* The fruit price by unit */
    private double unitPrice;
}
