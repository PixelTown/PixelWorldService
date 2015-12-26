package com.pixelworld.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pixelworld.domain.ForgeRecipe;
import com.pixelworld.domain.Inventory;
import com.pixelworld.service.InventoryService;
import com.pixelworld.service.RecipeService;
import com.pixelworld.utils.RecipeUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/26.
 */
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Inject
    private RecipeService recipeService;

    @Inject
    private InventoryService inventoryService;

    @Inject
    private RecipeUtils recipeUtils;

    @RequestMapping(value = "/addforge", method = RequestMethod.POST)
    public HttpEntity<ForgeRecipe> addForgeRecipe(@RequestBody ForgeRecipe forgeRecipe){
        ForgeRecipe fr = recipeService.saveForgeRecipe(forgeRecipe);
        return new ResponseEntity<ForgeRecipe>(fr, HttpStatus.OK);
    }

    @RequestMapping(value = "/getforge", method = RequestMethod.POST)
    public HttpEntity<String> getForgeRecipe(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        Inventory inventory = inventoryService.findByUsername(username.get("username").getAsString());
        if(inventory == null)
            return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
        List<ForgeRecipe> forgeRecipes = recipeService.findAllForgeRecipe();
        forgeRecipes = recipeUtils.checkForgeAvailable(forgeRecipes, inventory);
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(forgeRecipes), HttpStatus.OK);
    }

}
