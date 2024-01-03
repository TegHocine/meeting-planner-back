package com.formation.meetingplanner.equipment;

import com.formation.meetingplanner.enums.EquipmentName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
    List<Equipment> findByNameIn(List<EquipmentName> equipmentNames);
}
