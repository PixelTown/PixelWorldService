package com.pixelworld.service;

import com.pixelworld.domain.ForgeRecipe;
import com.pixelworld.repository.ForgeRecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/26.
 */
@Service
public class RecipeService {

    private final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Inject
    private ForgeRecipeRepository forgeRecipeRepository;

    public List<ForgeRecipe> findAllForgeRecipe(){
        return forgeRecipeRepository.findAll();
    }

    public ForgeRecipe findOneForgeRecipe(String name){
        return forgeRecipeRepository.findOneByName(name);
    }

    public ForgeRecipe saveForgeRecipe(ForgeRecipe forgeRecipe){
        return forgeRecipeRepository.save(forgeRecipe);
    }
}
