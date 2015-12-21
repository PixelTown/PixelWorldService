package com.pixelworld.controller;

import com.pixelworld.domain.Monster;
import com.pixelworld.service.MonsterService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by BladeInShine on 15/12/21.
 */

@RestController
@RequestMapping("/monster")
public class MonsterController {

    @Inject
    private MonsterService monsterService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity<Monster> addMonster(@RequestBody Monster monster){
        Monster m = monsterService.save(monster);
        return new ResponseEntity<Monster>(m, HttpStatus.OK);
    }


}
