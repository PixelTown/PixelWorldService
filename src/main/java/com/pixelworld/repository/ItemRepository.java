package com.pixelworld.repository;

import com.pixelworld.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BladeInShine on 15/12/23.
 */
public interface ItemRepository extends MongoRepository<Item, String> {

    Item findOneByName(String name);

    String deleteByName(String name);

}
