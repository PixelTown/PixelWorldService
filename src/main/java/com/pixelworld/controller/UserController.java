package com.pixelworld.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pixelworld.domain.User;
import com.pixelworld.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by BladeInShine on 15/12/13.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    public HttpEntity<String> getUserByUsername(@RequestBody String body){
        JsonObject username = (new JsonParser()).parse(body).getAsJsonObject();
        if(username.get("username").isJsonNull())
            return new ResponseEntity<String>("No Username Passed", HttpStatus.INTERNAL_SERVER_ERROR);
        User user = userService.findByUsername(username.get("username").getAsString());
        if(user == null)
            return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public HttpEntity<User> updateUserInfo(@RequestBody User user){
        User u = userService.save(user);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

}
