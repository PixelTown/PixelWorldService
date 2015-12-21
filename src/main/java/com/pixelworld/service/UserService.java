package com.pixelworld.service;

import com.pixelworld.domain.User;
import com.pixelworld.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/13.
 */
@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findOneByUsername(username);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public String deleteByUsername(String username){
        return userRepository.deleteByUsername(username);
    }

}
