package com.pixelworld.utils;

import com.pixelworld.domain.Equipment;
import com.pixelworld.domain.User;
import com.pixelworld.service.EquipmentService;
import com.pixelworld.service.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by BladeInShine on 15/12/21.
 */
@Service
public class UserUtils {

    @Inject
    private UserService userService;

    @Inject
    private EquipmentService equipmentService;

    public User calculateStats(String username){

        User user = userService.findByUsername(username);
        Equipment left = equipmentService.findOneByName(user.getLeft());
        Equipment right = equipmentService.findOneByName(user.getRight());
        Equipment head = equipmentService.findOneByName(user.getHead());
        Equipment body = equipmentService.findOneByName(user.getArmor());
        Equipment glove = equipmentService.findOneByName(user.getGlove());
        Equipment boot = equipmentService.findOneByName(user.getBoot());

        int newAtk = user.getAtk()+left.getAtk()+right.getAtk()+head.getAtk()+body.getAtk()+glove.getAtk()+boot.getAtk();
        int newDef = user.getDef()+left.getDef()+right.getDef()+head.getDef()+body.getDef()+glove.getDef()+boot.getDef();
        int newHp = user.getHp()+left.getHp()+right.getHp()+head.getHp()+body.getHp()+glove.getHp()+boot.getHp();

        user.setAtk(newAtk);
        user.setDef(newDef);
        user.setHp(newHp);

        return user;

    }

}
