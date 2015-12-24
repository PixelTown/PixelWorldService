package com.pixelworld.controller;

import com.pixelworld.domain.Item;
import com.pixelworld.service.ItemService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by BladeInShine on 15/12/24.
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Inject
    private ItemService itemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity<Item> addItem(@RequestBody Item item){
        Item i = itemService.save(item);
        return new ResponseEntity<Item>(i, HttpStatus.OK);
    }

}
