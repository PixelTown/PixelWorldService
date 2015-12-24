package com.pixelworld.repository;

import com.pixelworld.domain.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BladeInShine on 15/12/23.
 */
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    Inventory findOneByUsername(String username);

    String deleteByUsername(String username);

}
