package com.pixelworld.service;

import com.pixelworld.domain.Equipment;
import com.pixelworld.repository.EquipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/21.
 */
@Service
public class EquipmentService {

    private final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    @Inject
    private EquipmentRepository equipmentRepository;

    public List<Equipment> findAll(){
        return equipmentRepository.findAll();
    }

    public Equipment findOneByName(String name){
        return equipmentRepository.findOneByName(name);
    }

    public Equipment save(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

}
