package com.nicetoh8u.springrecipeapp.Service.Impl;

import com.nicetoh8u.springrecipeapp.Repositories.RecipeRepository;
import com.nicetoh8u.springrecipeapp.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeServiceImplTest {


    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        Set <Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        //when getRecipes is calling, then return this data
        Mockito.when(recipeService.getRecipes()).thenReturn(recipesData);


        Set <Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(),1);

        //we get find all only 1 time, not 0, not 2!
        Mockito.verify(recipeRepository,Mockito.times(1)).findAll();
    }
}