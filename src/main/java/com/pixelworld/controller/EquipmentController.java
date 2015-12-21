package com.pixelworld.controller;

import com.pixelworld.domain.Equipment;
import com.pixelworld.service.EquipmentService;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity<Equipment> addEquip(@RequestBody Equipment equipment){
        Equipment e = equipmentService.save(equipment);
        return new ResponseEntity<Equipment>(e, HttpStatus.OK);
    }

}
