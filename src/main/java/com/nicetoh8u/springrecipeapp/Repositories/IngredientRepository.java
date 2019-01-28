package com.nicetoh8u.springrecipeapp.Repositories;

import com.nicetoh8u.springrecipeapp.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface IngredientRepository extends CrudRepository <Ingredient,Long>  {

}
