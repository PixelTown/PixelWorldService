package com.pixelworld.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BladeInShine on 15/11/25.
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public HttpEntity<String> hello(@RequestParam(value = "name", defaultValue = "xiaofang gay")String name){
        return new ResponseEntity<String>("Hello "+name, HttpStatus.OK);
    }

}
