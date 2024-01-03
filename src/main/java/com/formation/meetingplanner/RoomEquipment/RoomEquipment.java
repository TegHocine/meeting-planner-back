package com.formation.meetingplanner.RoomEquipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formation.meetingplanner.enums.EquipmentName;
import com.formation.meetingplanner.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomEquipment {

    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private EquipmentName name;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

}
