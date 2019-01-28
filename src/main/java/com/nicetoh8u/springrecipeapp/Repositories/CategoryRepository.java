package com.nicetoh8u.springrecipeapp.Repositories;

import com.nicetoh8u.springrecipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByDescription(String description);
}
