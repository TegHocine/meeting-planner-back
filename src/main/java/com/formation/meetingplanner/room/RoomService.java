package com.formation.meetingplanner.room;

import com.formation.meetingplanner.dtos.RoomDto;
import com.formation.meetingplanner.mapper.RoomMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private  final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public List<RoomDto> getRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map((roomMapper::mapToRoomDto)).toList();
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoomById(UUID roomId) {
        roomRepository.deleteById(roomId);
    }

    public void addRooms(List<Room> rooms) {
        roomRepository.saveAll(rooms);
    }

}
