package com.pixelworld.repository;

import com.pixelworld.domain.ForgeRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BladeInShine on 15/12/26.
 */
public interface ForgeRecipeRepository extends MongoRepository<ForgeRecipe, String> {

    ForgeRecipe findOneByName(String name);

    String deleteByName(String name);

}
