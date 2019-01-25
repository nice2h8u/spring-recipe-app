package com.nicetoh8u.springrecipeapp.controllers;


import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String displayById(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }
}
