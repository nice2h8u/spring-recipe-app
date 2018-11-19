package com.nicetoh8u.springrecipeapp.Repositories;

import com.nicetoh8u.springrecipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
