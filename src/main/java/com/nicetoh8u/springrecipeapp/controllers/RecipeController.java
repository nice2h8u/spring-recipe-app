package com.nicetoh8u.springrecipeapp.controllers;


import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import com.nicetoh8u.springrecipeapp.commands.RecipeCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrDeleteRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savingCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/"+savingCommand.getId();
    }

    @RequestMapping("/recipe/update/{id}")
    public String updateById(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/recipeform";
    }

}
