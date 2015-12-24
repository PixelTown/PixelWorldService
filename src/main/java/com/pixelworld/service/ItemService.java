package com.pixelworld.service;

import com.pixelworld.domain.Item;
import com.pixelworld.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/24.
 */
@Service
public class ItemService {

    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Inject
    private ItemRepository itemRepository;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findByName(String name){
        return itemRepository.findOneByName(name);
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

}
