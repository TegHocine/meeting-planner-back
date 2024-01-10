package com.formation.meetingplanner.room;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.formation.meetingplanner.RoomEquipment.RoomEquipment;
import com.formation.meetingplanner.meeting.Meeting;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Integer capacity;
    @OneToMany(mappedBy = "room")
    @JsonManagedReference
    private List<Meeting> meetingList;
    @OneToMany(mappedBy = "room")
    private List<RoomEquipment> equipmentList;
}
