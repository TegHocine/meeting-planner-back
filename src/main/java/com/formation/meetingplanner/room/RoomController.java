package com.formation.meetingplanner.room;

import com.formation.meetingplanner.dtos.RoomDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/room")
public class RoomController {
    RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDto> getRooms(){
        return roomService.getRooms();
    }

    @PostMapping
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    @PostMapping(path = "add/all")
    public void addRooms(@RequestBody List<Room> rooms){
        roomService.addRooms(rooms);
    }

    @DeleteMapping(path = "{roomId}")
    public void deleteRoomById(@PathVariable("roomId") UUID roomId){
        roomService.deleteRoomById(roomId);
    }

}
