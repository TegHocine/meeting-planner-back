package com.formation.meetingplanner.room;

import com.formation.meetingplanner.dtos.RoomDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    private String capitalize(String string){
        return string.toUpperCase().charAt(0) +
                string.toLowerCase().substring(1);
    }
    private RoomDto mapToRoomDto(Room room){
        return  RoomDto.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .name(room.getName())
                .meetingList(room.getMeetingList())
                .equipment(
                        room.
                        getEquipmentList()
                        .stream()
                        .map(roomEquipment ->
                                capitalize(roomEquipment
                                .getName()
                                .toString())
                        )
                        .collect(Collectors.joining("+"))
                        )
                .build();
    }

    public List<RoomDto> getRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::mapToRoomDto).toList();
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


    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
}
