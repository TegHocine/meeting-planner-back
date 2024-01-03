package com.formation.meetingplanner.RoomEquipment;

import com.formation.meetingplanner.room.Room;
import com.formation.meetingplanner.room.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomEquipmentService {
    private final RoomEquipmentRepository roomEquipmentRepository;
    private final RoomRepository roomRepository;

    public RoomEquipmentService(RoomEquipmentRepository roomEquipmentRepository, RoomRepository roomRepository) {
        this.roomEquipmentRepository = roomEquipmentRepository;
        this.roomRepository = roomRepository;
    }

    public  void addRoomEquipment(UUID roomId, RoomEquipment equipment) {
        Room room = roomRepository.findById(roomId).orElseThrow(()-> new IllegalStateException("no room with that id"));
        equipment.setRoom(room);
        roomEquipmentRepository.save(equipment);
    }

    public List<RoomEquipment> getRoomEquipment() {
        return roomEquipmentRepository.findAll();
    }
}
