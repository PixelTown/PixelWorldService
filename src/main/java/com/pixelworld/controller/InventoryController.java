package com.pixelworld.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.pixelworld.domain.Equipment;
import com.pixelworld.domain.Inventory;
import com.pixelworld.domain.Item;
import com.pixelworld.service.InventoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/24.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Inject
    private InventoryService inventoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public HttpEntity<String> createInventory(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        Inventory inventory = new Inventory();
        inventory.setUsername(username.get("username").getAsString());
        List<Equipment> equipments = new ArrayList<>();
        inventory.setEquipments(equipments);
        List<Item> items = new ArrayList<>();
        inventory.setItems(items);
        inventory = inventoryService.save(inventory);
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(inventory), HttpStatus.OK);

    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public HttpEntity<String> getInventory(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        Inventory inventory = inventoryService.findByUsername(username.get("username").getAsString());
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(inventory), HttpStatus.OK);
    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    public HttpEntity<String> addItem(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        List<String> items = gson.fromJson(username.get("items"), type);
        Inventory inventory = inventoryService.addItem(username.get("username").getAsString(), items);
        return new ResponseEntity<String>(gson.toJson(inventory), HttpStatus.OK);
    }

    @RequestMapping(value = "/delitem", method = RequestMethod.POST)
    public HttpEntity<String> delItem(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        List<String> items = gson.fromJson(username.get("items"), type);
        Inventory inventory = inventoryService.deleteItem(username.get("username").getAsString(), items);
        return new ResponseEntity<String>(gson.toJson(inventory), HttpStatus.OK);
    }

    @RequestMapping(value = "/addequip", method = RequestMethod.POST)
    public HttpEntity<String> addEquip(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        List<String> equips = gson.fromJson(username.get("equips"), type);
        Inventory inventory = inventoryService.addEquip(username.get("username").getAsString(), equips);
        return new ResponseEntity<String>(gson.toJson(inventory), HttpStatus.OK);
    }

    @RequestMapping(value = "/delequip", method = RequestMethod.POST)
    public HttpEntity<String> delEquip(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        List<String> equips = gson.fromJson(username.get("equips"), type);
        Inventory inventory = inventoryService.deleteEquip(username.get("username").getAsString(), equips);
        return new ResponseEntity<String>(gson.toJson(inventory), HttpStatus.OK);
    }

}
