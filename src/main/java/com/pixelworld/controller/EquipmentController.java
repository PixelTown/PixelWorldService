package com.pixelworld.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pixelworld.domain.Equipment;
import com.pixelworld.service.EquipmentService;
import com.pixelworld.utils.EquipUtils;
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
@RequestMapping("/equip")
public class EquipmentController {

    @Inject
    private EquipmentService equipmentService;

    @Inject
    private EquipUtils equipUtils;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity<Equipment> addEquip(@RequestBody Equipment equipment){
        Equipment e = equipmentService.save(equipment);
        return new ResponseEntity<Equipment>(e, HttpStatus.OK);
    }

    @RequestMapping(value = "/wear", method = RequestMethod.POST)
    public HttpEntity<String> wearEquip(@RequestBody String body){
        JsonObject info = (new JsonParser()).parse(body).getAsJsonObject();
        if(info.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        if(info.get("equipment").isJsonNull())
            return new ResponseEntity<String>("No Equipment Name Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        if(info.get("itemid").isJsonNull())
            return new ResponseEntity<String>("No Item ID Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        if(info.get("field").isJsonNull())
            return new ResponseEntity<String>("No Field Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        String username = info.get("username").getAsString();
        String equipment = info.get("equipment").getAsString();
        int itemid = info.get("itemid").getAsInt();
        String field = info.get("field").getAsString();
        String res = equipUtils.wearEquip(username, equipment, itemid, field);
        JsonObject ret = new JsonObject();
        ret.addProperty("status", res);
        return new ResponseEntity<String>(ret.toString(),HttpStatus.OK);
    }

    @RequestMapping(value = "/unequip", method = RequestMethod.POST)
    public HttpEntity<String> unequip(@RequestBody String body){
        JsonObject info = (new JsonParser()).parse(body).getAsJsonObject();
        if(info.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        if(info.get("field").isJsonNull())
            return new ResponseEntity<String>("No Field Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        String username = info.get("username").getAsString();
        String field = info.get("field").getAsString();
        String res = equipUtils.unequip(username, field);
        JsonObject ret = new JsonObject();
        ret.addProperty("status", res);
        return new ResponseEntity<String>(ret.toString(),HttpStatus.OK);
    }

}
