package com.nicetoh8u.springrecipeapp.Service.Impl;

import com.nicetoh8u.springrecipeapp.Repositories.RecipeRepository;
import com.nicetoh8u.springrecipeapp.Service.IngredientService;
import com.nicetoh8u.springrecipeapp.commands.IngredientCommand;
import com.nicetoh8u.springrecipeapp.convertes.IngredientToIngredientCommand;
import com.nicetoh8u.springrecipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;


    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }


    public IngredientCommand findByRecipeId(Long recipeId, Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (!recipe.isPresent())
            throw new RuntimeException("Can't find recipe with such id!");

        Optional<IngredientCommand> ingredientCommand = recipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommand.isPresent())
            throw new RuntimeException("Such ingredient id not present!");

        return ingredientCommand.get();
    }
}
