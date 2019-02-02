package com.nicetoh8u.springrecipeapp.controllers;

import com.nicetoh8u.springrecipeapp.Exception.NotFoundException;
import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import com.nicetoh8u.springrecipeapp.commands.RecipeCommand;
import com.nicetoh8u.springrecipeapp.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ExceptionController())
                .build();
    }

    @Test
    public void displayById() throws Exception {
        Recipe testRecipe = new Recipe();
        testRecipe.setId(1L);


        when(recipeService.findById(anyLong())).thenReturn(testRecipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));

    }


    @Test
    public void testRecipePost() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","2")
                .param("description","somestring")
                .param("directions","some direction")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/show/"+recipeCommand.getId()));

    }

    @Test
    public void deleteByIdTest() throws Exception{

        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService).deleteById(anyLong());

    }


    @Test
    public void recipeNotFound() throws Exception{
        when(recipeService.findById(anyLong())).thenThrow(new NotFoundException());

        mockMvc.perform(get("/recipe/3/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void recipeNumberFormat() throws Exception{
        when(recipeService.findById(anyLong())).thenThrow(new NumberFormatException());

        mockMvc.perform(get("/recipe/ddsd/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}