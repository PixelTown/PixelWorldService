package com.pixelworld.repository;

import com.pixelworld.domain.Monster;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BladeInShine on 15/12/21.
 */
public interface MonsterRepository extends MongoRepository<Monster, String> {

    Monster findOneByName(String name);

    Monster findOneByFloor(int floor);

}
