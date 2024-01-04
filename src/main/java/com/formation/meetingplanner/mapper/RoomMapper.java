package com.formation.meetingplanner.mapper;

import com.formation.meetingplanner.dtos.RoomDto;
import com.formation.meetingplanner.room.Room;
import com.formation.meetingplanner.utils.Capitalize;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomMapper {

    private final Capitalize capitalize;

    public RoomMapper(Capitalize capitalize) {
        this.capitalize = capitalize;
    }



   public RoomDto mapToRoomDto(Room room){
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
                                        capitalize.capitalize(
                                                roomEquipment
                                                        .getName()
                                                        git .toString()
                                        )
                                )
                                .collect(Collectors.joining(" + "))
                )
                .build();
    }
}
