package com.nicetoh8u.springrecipeapp.Service.Impl;

import com.nicetoh8u.springrecipeapp.Repositories.RecipeRepository;
import com.nicetoh8u.springrecipeapp.Service.ImageService;
import com.nicetoh8u.springrecipeapp.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public void save(Long id, MultipartFile file) {
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(id);
            if (recipeOptional.isPresent()) {
                Recipe recipe = recipeOptional.get();
                Byte[] bytes = new Byte[file.getBytes().length];

                int i = 0;
                for (byte b : file.getBytes()) {
                    bytes[i++] = b;
                }

                recipe.setImage(bytes);
                recipeRepository.save(recipe);
            }

            else log.debug("No recipe with id " + id);

        } catch (IOException e) {
            log.debug("error in image uploading");

            e.printStackTrace();
        }
    }
}
