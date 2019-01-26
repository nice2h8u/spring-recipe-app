package com.nicetoh8u.springrecipeapp.Service.Impl;

import com.nicetoh8u.springrecipeapp.Repositories.RecipeRepository;
import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import com.nicetoh8u.springrecipeapp.commands.RecipeCommand;
import com.nicetoh8u.springrecipeapp.convertes.RecipeCommandToRecipe;
import com.nicetoh8u.springrecipeapp.convertes.RecipeToRecipeCommand;
import com.nicetoh8u.springrecipeapp.model.Recipe;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;
    RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    public Recipe findById(Long id) {
        Optional <Recipe> recipe = recipeRepository.findById(id);

        if(!recipe.isPresent())
            throw new RuntimeException("Recipe isn't present");

        return recipe.get();
    }

   @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe savingRecape = recipeCommandToRecipe.convert(recipeCommand);
        recipeRepository.save(savingRecape);
        log.debug("Saving recipe with id " + savingRecape.getId());
        return recipeToRecipeCommand.convert(savingRecape) ;
    }


    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    public void deleteById(Long id){
        recipeRepository.deleteById(id);
    }
}
