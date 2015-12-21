package com.pixelworld.service;

import com.pixelworld.domain.Monster;
import com.pixelworld.repository.MonsterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/21.
 */

@Service
public class MonsterService {

    private final Logger logger = LoggerFactory.getLogger(MonsterService.class);

    @Inject
    private MonsterRepository monsterRepository;

    public List<Monster> findAll(){
        return monsterRepository.findAll();
    }

    public Monster findByName(String name){
        return monsterRepository.findOneByName(name);
    }

    public Monster save(Monster monster){
        return monsterRepository.save(monster);
    }

    public Monster findByFloor(int floor){
        return monsterRepository.findOneByFloor(floor);
    }

}
