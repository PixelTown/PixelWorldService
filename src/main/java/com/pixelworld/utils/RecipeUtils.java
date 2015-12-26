package com.pixelworld.utils;

import com.pixelworld.domain.Equipment;
import com.pixelworld.domain.ForgeRecipe;
import com.pixelworld.domain.Inventory;
import com.pixelworld.domain.Item;
import com.pixelworld.service.EquipmentService;
import com.pixelworld.service.InventoryService;
import com.pixelworld.service.RecipeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/26.
 */
@Service
public class RecipeUtils {

    @Inject
    private RecipeService recipeService;

    @Inject
    private InventoryService inventoryService;

    @Inject
    private EquipmentService equipmentService;

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

    public String forge(String username, String name){
        ForgeRecipe forgeRecipe = recipeService.findOneForgeRecipe(name);
        Inventory inventory = inventoryService.findByUsername(username);
        List<Item> userItems = inventory.getItems();
        for(String str : forgeRecipe.getItems()){
            int j = 0;
            for(Item i : userItems){
                if(str.equals(i.getName())){
                    userItems.remove(j);
                    break;
                }
                j++;
            }
        }
        List<Equipment> userEquips = inventory.getEquipments();
        userEquips.add(equipmentService.findOneByName(name));
        inventory.setEquipments(userEquips);
        inventory.setItems(userItems);
        inventoryService.deleteByUsername(username);
        inventoryService.save(inventory);
        return "success";
    }

}
