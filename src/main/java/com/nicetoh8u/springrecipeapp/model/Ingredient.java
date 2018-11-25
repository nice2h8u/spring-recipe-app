package com.nicetoh8u.springrecipeapp.model;



import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal count;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uOM;

    public Ingredient(){

    }
    public Ingredient(String description, BigDecimal count, UnitOfMeasure uOM) {
        this.description = description;
        this.count = count;
        this.uOM = uOM;
    }

    public Ingredient(String description, BigDecimal count, Recipe recipe, UnitOfMeasure uOM) {
        this.description = description;
        this.count = count;
        this.recipe = recipe;
        this.uOM = uOM;
    }

}
