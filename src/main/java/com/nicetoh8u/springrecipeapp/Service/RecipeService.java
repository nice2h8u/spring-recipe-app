package com.nicetoh8u.springrecipeapp.Service;

import com.nicetoh8u.springrecipeapp.commands.RecipeCommand;
import com.nicetoh8u.springrecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {



    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findCommandById(Long l);
    void deleteById(Long id);
}
