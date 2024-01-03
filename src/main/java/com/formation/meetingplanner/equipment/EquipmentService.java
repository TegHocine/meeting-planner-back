package com.formation.meetingplanner.equipment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getEquipment() {
        return equipmentRepository.findAll();
    }

    public void addEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    public void addEquipments(List<Equipment> equipments) {
        equipmentRepository.saveAll(equipments);
    }
}
