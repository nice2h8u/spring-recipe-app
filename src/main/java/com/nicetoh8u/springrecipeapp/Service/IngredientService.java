package com.nicetoh8u.springrecipeapp.Service;

import com.nicetoh8u.springrecipeapp.commands.IngredientCommand;
import com.nicetoh8u.springrecipeapp.model.Ingredient;

import java.util.Set;

public interface IngredientService {

    IngredientCommand findByRecipeId(Long recipeId,Long id);
}
