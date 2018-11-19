package com.nicetoh8u.springrecipeapp.model;



import javax.persistence.*;
import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getuOM() {
        return uOM;
    }

    public void setuOM(UnitOfMeasure uOM) {
        this.uOM = uOM;
    }
}
