package com.pixelworld.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pixelworld.domain.Monster;
import com.pixelworld.domain.User;
import com.pixelworld.service.InventoryService;
import com.pixelworld.service.MonsterService;
import com.pixelworld.service.UserService;
import com.pixelworld.utils.BattleUtils;
import com.pixelworld.utils.UserUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/21.
 */
@RestController
@RequestMapping("/battle")
public class BattleController {

    @Inject
    private UserService userService;

    @Inject
    private MonsterService monsterService;

    @Inject
    private UserUtils userUtils;

    @Inject
    private BattleUtils battleUtils;

    @Inject
    private InventoryService inventoryService;

    @RequestMapping(value = "/monster", method = RequestMethod.POST)
    public HttpEntity<String> queryMonster(@RequestBody String body){
        JsonObject info = (new JsonParser()).parse(body).getAsJsonObject();
        if(info.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        if(info.get("floor").isJsonNull())
            return new ResponseEntity<String>("No Floor Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        User user = userService.findByUsername(info.get("username").getAsString());
        if(user == null)
            return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
        user.setFloor(info.get("floor").getAsInt());
        userService.deleteByUsername(user.getUsername());
        userService.save(user);
        Monster monster = monsterService.findByFloor(info.get("floor").getAsInt());
        if(monster == null)
            return new ResponseEntity<String>("Monster or Floor Not Found", HttpStatus.NOT_FOUND);
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(monster), HttpStatus.OK);

    }

    @RequestMapping(value = "/fight", method = RequestMethod.POST)
    public HttpEntity<String> fightMonster(@RequestBody String body){
        JsonObject info = (new JsonParser()).parse(body).getAsJsonObject();
        if(info.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        if(info.get("floor").isJsonNull())
            return new ResponseEntity<String>("No Floor Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        User user = userUtils.calculateStats(info.get("username").getAsString());
        if(user == null)
            return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
        Monster monster = monsterService.findByFloor(info.get("floor").getAsInt());
        if(monster == null)
            return new ResponseEntity<String>("Monster or Floor Not Found", HttpStatus.NOT_FOUND);
        String res = battleUtils.battleCalculation(user, monster);
        JsonObject ret = new JsonObject();
        List<String> drops = new ArrayList<>();
        drops.add(res);
        inventoryService.addItem(info.get("username").getAsString(), drops);
        if(res.equals("failed")){
            ret.addProperty("drop", "failed");
        }
        else{
            ret.addProperty("drop", res);
        }
        return new ResponseEntity<String>(ret.toString(), HttpStatus.OK);

    }

}
