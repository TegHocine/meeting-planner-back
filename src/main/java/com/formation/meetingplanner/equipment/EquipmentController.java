package com.formation.meetingplanner.equipment;

import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public List<Equipment> getEquipment(){
        return equipmentService.getEquipment();
    }

    @PostMapping
    public void addEquipment(@RequestBody Equipment equipment){
        equipmentService.addEquipment(equipment);
    }

    @PostMapping(path = "add/all")
    public void addEquipment(@RequestBody List<Equipment> equipments){
        equipmentService.addEquipments(equipments);
    }
}
