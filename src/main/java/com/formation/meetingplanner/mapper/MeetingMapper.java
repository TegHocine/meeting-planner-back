package com.formation.meetingplanner.mapper;

import com.formation.meetingplanner.dtos.MeetingDto;
import com.formation.meetingplanner.meeting.Meeting;
import com.formation.meetingplanner.utils.Capitalize;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MeetingMapper {

    private final RoomMapper roomMapper;
    private  final Capitalize capitalize;

    public MeetingMapper(RoomMapper roomMapper, Capitalize capitalize) {
        this.roomMapper = roomMapper;
        this.capitalize = capitalize;
    }



    public MeetingDto mapToMeetingDto(Meeting meeting){
        String message = "You got the room : " +
                meeting.getRoom().getName();
        return MeetingDto.builder()
                .date(meeting.getDate())
                .endTime(meeting.getEndTime())
                .id(meeting.getId())
                .startTime(meeting.getStartTime())
                .nbrPeople(meeting.getNbrPeople())
                .type(meeting.getType())
                .room(roomMapper.mapToRoomDto(meeting.getRoom()))
                .message(message)
                .equipmentList(meeting.getEquipmentList()
                        .stream()
                        .map(roomEquipment ->
                                capitalize
                                        .capitalize(
                                                roomEquipment
                                                        .getName()
                                                        .toString()
                                        )
                        )
                        .collect(Collectors.joining(" + ")))
                .build();
    }


}
