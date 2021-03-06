package com.nicetoh8u.springrecipeapp.Service.Impl;

import com.nicetoh8u.springrecipeapp.Exception.NotFoundException;
import com.nicetoh8u.springrecipeapp.Repositories.IngredientRepository;
import com.nicetoh8u.springrecipeapp.Repositories.RecipeRepository;
import com.nicetoh8u.springrecipeapp.Repositories.UnitOfMeasureRepository;
import com.nicetoh8u.springrecipeapp.Service.IngredientService;
import com.nicetoh8u.springrecipeapp.commands.IngredientCommand;
import com.nicetoh8u.springrecipeapp.convertes.IngredientCommandToIngredient;
import com.nicetoh8u.springrecipeapp.convertes.IngredientToIngredientCommand;
import com.nicetoh8u.springrecipeapp.model.Ingredient;
import com.nicetoh8u.springrecipeapp.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository, IngredientCommandToIngredient ingredientCommandToIngredient, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    public IngredientCommand findByRecipeId(Long recipeId, Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (!recipe.isPresent())
            throw new NotFoundException("Can't find recipe with such id!");

        Optional<IngredientCommand> ingredientCommand = recipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommand.isPresent())
            throw new NotFoundException("Such ingredient id not present!");

        return ingredientCommand.get();
    }

    @Transactional
    public IngredientCommand saveOrUpdateIngredient(IngredientCommand command) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());
        if (!optionalRecipe.isPresent()) {
            log.debug("No recipe with such id");
            return new IngredientCommand();
        }
        Recipe recipe = optionalRecipe.get();

        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setCount(command.getCount());
            ingredient.setDescription(command.getDescription());
            ingredient.setUOM(unitOfMeasureRepository.findById(command.getUOM().getId())
                    .orElseThrow(() -> new NotFoundException("No such uom!")));

        } else {
            Ingredient ingredient = ingredientCommandToIngredient.convert(command);
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }
        Recipe savedRecipe = recipeRepository.save(recipe);

        Optional <Ingredient> savingIngridient = savedRecipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();

        if(!savingIngridient.isPresent()){
            savingIngridient = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                    .filter(ingredient -> ingredient.getCount().equals(command.getCount()))
                    .filter(ingredient -> ingredient.getUOM().getId().equals(command.getUOM().getId()))
                    .findFirst();
        }

return ingredientToIngredientCommand.convert(savingIngridient.get());
    }

    public void deleteById(Long id){
        ingredientRepository.deleteById(id);
    }
}
