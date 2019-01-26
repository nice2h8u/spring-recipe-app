package com.nicetoh8u.springrecipeapp.controllers;

import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    @Autowired
   private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("")
    public String index(Model model){
       model.addAttribute("recipes",recipeService.getRecipes());
        log.debug("getting index page");
        return "index";
    }
}