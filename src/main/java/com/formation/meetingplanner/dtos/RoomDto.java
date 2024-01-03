package com.formation.meetingplanner.dtos;

import com.formation.meetingplanner.meeting.Meeting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private UUID id;
    private String name;
    private Integer capacity;
    private List<Meeting> meetingList;
    private String equipment;

}
