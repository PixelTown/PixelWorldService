package com.pixelworld.utils;

import com.pixelworld.domain.ForgeRecipe;
import com.pixelworld.domain.Inventory;
import com.pixelworld.domain.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/26.
 */
@Service
public class RecipeUtils {

    public List<ForgeRecipe> checkForgeAvailable(List<ForgeRecipe> recipes, Inventory inventory){
        List<Item> userItems = inventory.getItems();
        HashMap<String, Integer> hm = new HashMap<>();
        for(Item i : userItems){
            if(hm.containsKey(i.getName()))
                hm.put(i.getName(), hm.get(i.getName())+1);
            else
                hm.put(i.getName(), 1);
        }
        for(ForgeRecipe fr : recipes){
            boolean found = true;
            for(String name : fr.getItems()){
                found = false;
                if(hm.containsKey(name)){
                    if(hm.get(name) > 0) {
                        found = true;
                    }
                    hm.put(name, hm.get(name) - 1);
                }
                if(!found) break;
            }
            if(found) fr.setAvailable("t");
            else fr.setAvailable("f");
            for(String name : fr.getItems()){
                if(hm.containsKey(name)){
                    hm.put(name, hm.get(name) + 1);
                }
            }
        }
        return recipes;
    }

}
