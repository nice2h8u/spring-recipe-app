package com.nicetoh8u.springrecipeapp.Repositories;

import com.nicetoh8u.springrecipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
