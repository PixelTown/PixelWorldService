package com.pixelworld.repository;

import com.pixelworld.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BladeInShine on 15/12/13.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findOneByUsername(String username);

}
