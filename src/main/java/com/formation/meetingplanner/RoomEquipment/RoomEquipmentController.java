package com.formation.meetingplanner.RoomEquipment;

import com.formation.meetingplanner.meeting.Meeting;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/room_equipment")
public class RoomEquipmentController {

    private final RoomEquipmentService roomEquipmentService;

    public RoomEquipmentController(RoomEquipmentService roomEquipmentService) {
        this.roomEquipmentService = roomEquipmentService;
    }

    @GetMapping
    public List<RoomEquipment> getRoomEquipment(){
        return roomEquipmentService.getRoomEquipment();
    }

    @PostMapping(path = "{roomId}")
    public void addRoomEquipment(@PathVariable("roomId") UUID room_id, @RequestBody RoomEquipment equipment){
        roomEquipmentService.addRoomEquipment(room_id, equipment);
    }
}
