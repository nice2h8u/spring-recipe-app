package com.nicetoh8u.springrecipeapp.controllers;

import com.nicetoh8u.springrecipeapp.Service.IngredientService;
import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import com.nicetoh8u.springrecipeapp.Service.UnitOfMeasureService;
import com.nicetoh8u.springrecipeapp.commands.IngredientCommand;
import com.nicetoh8u.springrecipeapp.commands.RecipeCommand;
import com.nicetoh8u.springrecipeapp.commands.UnitOfMeasureCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {


   private final RecipeService recipeService;
   private final IngredientService ingredientService;
   private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String getIngredinetsOfRecipe(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(new Long(recipeId)));
        log.debug("getting ingredients of ");
        return "recipe/ingredient/list";
    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String getShowIgredient(@PathVariable String recipeId,@PathVariable String id, Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeId(Long.parseLong(recipeId),Long.parseLong(id)));

        return "recipe/ingredient/show";
    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIgredient(@PathVariable String recipeId,@PathVariable String id, Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeId(Long.parseLong(recipeId),Long.parseLong(id)));
        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping()
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String id, Model model){

        ingredientService.deleteById(Long.parseLong(id));

        return "redirect:/recipe/{recipeId}/ingredients";
    }

    @GetMapping()
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.parseLong(recipeId));
        model.addAttribute("ingredient",ingredientCommand);

        ingredientCommand.setUOM(new UnitOfMeasureCommand());

        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());


        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrDeleteRecipe(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savingCommand = ingredientService.saveOrUpdateIngredient(ingredientCommand);

        return "redirect:/recipe/"+savingCommand.getRecipeId()+"/ingredient/"+savingCommand.getId()+"/show";
    }



}
