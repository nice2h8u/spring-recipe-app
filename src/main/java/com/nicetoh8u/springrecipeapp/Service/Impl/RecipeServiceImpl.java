package com.nicetoh8u.springrecipeapp.Service.Impl;

import com.nicetoh8u.springrecipeapp.Repositories.RecipeRepository;
import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import com.nicetoh8u.springrecipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}
