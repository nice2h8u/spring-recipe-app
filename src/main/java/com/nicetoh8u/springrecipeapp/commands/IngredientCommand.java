package com.nicetoh8u.springrecipeapp.commands;

import com.nicetoh8u.springrecipeapp.model.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal count;
    private UnitOfMeasureCommand uOM;
    private Long recipeId;

}