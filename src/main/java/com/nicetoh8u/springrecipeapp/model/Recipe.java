package com.nicetoh8u.springrecipeapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepareTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String direction;

    @Lob
    private Byte image;

    @Enumerated(value = EnumType.STRING)
     private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") //if delete recipe, and no links to ingridient, delete this ingridient
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name ="category_id"))
    private Set<Category> categories=new HashSet<>();

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
