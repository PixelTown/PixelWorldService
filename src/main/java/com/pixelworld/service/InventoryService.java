package com.pixelworld.service;

import com.pixelworld.domain.Inventory;
import com.pixelworld.domain.Item;
import com.pixelworld.repository.InventoryRepository;
import com.pixelworld.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/23.
 */
@Service
public class InventoryService {


    private final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Inject
    private InventoryRepository inventoryRepository;

    @Inject
    private ItemRepository itemRepository;

    public Inventory findByUsername(String username){
        return inventoryRepository.findOneByUsername(username);
    }

    public String deleteByUsername(String username){
        return inventoryRepository.deleteByUsername(username);
    }

    public Inventory addItem(String username, List<String> items){
        Inventory inv = inventoryRepository.findOneByUsername(username);
        List<Item> userItems = inv.getItems();
        for(String i : items){
            userItems.add(itemRepository.findOneByName(i));
        }
        inv.setItems(userItems);
        inventoryRepository.deleteByUsername(username);
        return inventoryRepository.save(inv);
    }

    public Inventory deleteItem(String username, List<String> items){
        Inventory inv = inventoryRepository.findOneByUsername(username);
        List<Item> userItems = inv.getItems();
        for(String i : items){
            int j = 0;
            for(Item item : userItems){
                if(item.getName().equals(i)){
                    userItems.remove(j);
                    break;
                }
                j++;
            }
        }
        inv.setItems(userItems);
        inventoryRepository.deleteByUsername(username);
        return inventoryRepository.save(inv);
    }


    public Inventory save(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

}
