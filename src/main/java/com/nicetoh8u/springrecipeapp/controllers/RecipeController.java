package com.nicetoh8u.springrecipeapp.controllers;


import com.nicetoh8u.springrecipeapp.Exception.NotFoundException;
import com.nicetoh8u.springrecipeapp.Service.RecipeService;
import com.nicetoh8u.springrecipeapp.commands.RecipeCommand;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    @RequestMapping("/recipe/{id}/show")
    public String displayById(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }

    @GetMapping()
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

    @GetMapping()
    @RequestMapping("/recipe/{id}/update")
    public String updateById(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/recipeform";
    }

    @GetMapping()
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id, Model model){

        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView getNotFoundException(NotFoundException exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }




}
