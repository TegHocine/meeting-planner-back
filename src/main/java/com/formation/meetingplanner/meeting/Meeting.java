package com.formation.meetingplanner.meeting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formation.meetingplanner.enums.MeetingType;
import com.formation.meetingplanner.equipment.Equipment;
import com.formation.meetingplanner.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Integer nbrPeople;
    @Enumerated(EnumType.STRING)
    private MeetingType type;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "meeting_equipment"
            ,joinColumns = { @JoinColumn(name = "meeting_id", referencedColumnName = "id") }
            ,inverseJoinColumns = { @JoinColumn(name = "equipment_id",referencedColumnName = "id") }
    )
    private List<Equipment> equipmentList;

}
