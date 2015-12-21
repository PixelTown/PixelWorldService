package com.pixelworld.repository;

import com.pixelworld.domain.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BladeInShine on 15/12/21.
 */
public interface EquipmentRepository extends MongoRepository<Equipment, String>{

    Equipment findOneByName(String name);

}
