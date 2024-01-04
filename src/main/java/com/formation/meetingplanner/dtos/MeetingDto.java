package com.formation.meetingplanner.dtos;

import com.formation.meetingplanner.enums.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDto {
    private UUID id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Integer nbrPeople;
    private MeetingType type;
    private RoomDto room;
    private String equipmentList;
}
