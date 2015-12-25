package com.pixelworld.utils;

import com.pixelworld.domain.Equipment;
import com.pixelworld.domain.Inventory;
import com.pixelworld.domain.User;
import com.pixelworld.service.EquipmentService;
import com.pixelworld.service.InventoryService;
import com.pixelworld.service.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/25.
 */
@Service
public class EquipUtils {

    @Inject
    private UserService userService;

    @Inject
    private EquipmentService equipmentService;

    @Inject
    private InventoryService inventoryService;

    public String wearEquip(String username, String equipment, int itemid, String field){
        User user = userService.findByUsername(username);
        Equipment equip = equipmentService.findOneByName(equipment);
        Inventory inventory = inventoryService.findByUsername(username);
        List<Equipment> userEquips = inventory.getEquipments();
        if(equip.getField().equals("onehanded")){
            if(!field.equals("left") && !field.equals("right")){
                return "invalid field";
            }
        }
        else{
            if(!equip.getField().equals(field)){
                return "invalid field";
            }
        }

        if(itemid - inventory.getItems().size() >= userEquips.size())
            return "invalid item index";
        switch(field){
            case "left":
                userEquips.remove(itemid - inventory.getItems().size());
                if(!user.getLeft().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getLeft()));
                }
                user.setLeft(equipment);
                break;
            case "right":
                userEquips.remove(itemid - inventory.getItems().size());
                if(!user.getRight().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getRight()));
                }
                user.setRight(equipment);
                break;
            case "head":
                userEquips.remove(itemid - inventory.getItems().size());
                if(!user.getHead().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getHead()));
                }
                user.setHead(equipment);
                break;
            case "armor":
                userEquips.remove(itemid - inventory.getItems().size());
                if(!user.getArmor().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getArmor()));
                }
                user.setArmor(equipment);
                break;
            case "boot":
                userEquips.remove(itemid - inventory.getItems().size());
                if(!user.getBoot().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getBoot()));
                }
                user.setBoot(equipment);
                break;
            case "glove":
                userEquips.remove(itemid - inventory.getItems().size());
                if(!user.getGlove().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getGlove()));
                }
                user.setGlove(equipment);
                break;
            case "twohanded":
                userEquips.remove(itemid - inventory.getItems().size());
                if(!user.getLeft().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getLeft()));
                }
                user.setLeft("twohanded");
                if(user.getRight().equals("null")){
                    userEquips.add(equipmentService.findOneByName(user.getRight()));
                }
                user.setRight(equipment);
                break;
            default:
                return "invalid field";
        }
        userService.deleteByUsername(username);
        userService.save(user);
        inventory.setEquipments(userEquips);
        inventoryService.deleteByUsername(username);
        inventoryService.save(inventory);
        return "success";
    }

    public String unequip(String username, String field){
        User user = userService.findByUsername(username);
        Inventory inventory = inventoryService.findByUsername(username);
        List<Equipment> userEquips = inventory.getEquipments();
        switch (field){
            case "left":
                Equipment equip = equipmentService.findOneByName(user.getLeft());
                userEquips.add(equip);
                user.setLeft("null");
                break;
            case "right":
                equip = equipmentService.findOneByName(user.getRight());
                userEquips.add(equip);
                if(user.getLeft().equals("twohanded")) user.setLeft("null");
                user.setRight("null");
                break;
            case "head":
                equip = equipmentService.findOneByName(user.getHead());
                userEquips.add(equip);
                user.setHead("null");
                break;
            case "armor":
                equip = equipmentService.findOneByName(user.getArmor());
                userEquips.add(equip);
                user.setArmor("null");
                break;
            case "boot":
                equip = equipmentService.findOneByName(user.getBoot());
                userEquips.add(equip);
                user.setBoot("null");
                break;
            case "glove":
                equip = equipmentService.findOneByName(user.getGlove());
                userEquips.add(equip);
                user.setGlove("null");
                break;
            default:
                return "invalid field";
        }
        userService.deleteByUsername(username);
        userService.save(user);
        inventory.setEquipments(userEquips);
        inventoryService.deleteByUsername(username);
        inventoryService.save(inventory);
        return "success";
    }

}
