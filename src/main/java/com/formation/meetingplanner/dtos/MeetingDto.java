package com.formation.meetingplanner.dtos;

import com.formation.meetingplanner.enums.MeetingType;
import com.formation.meetingplanner.equipment.Equipment;
import com.formation.meetingplanner.room.Room;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
    private String message;
    private RoomDto room;
    private String equipmentList;
}
