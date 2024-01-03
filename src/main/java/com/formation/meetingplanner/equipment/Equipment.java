package com.formation.meetingplanner.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formation.meetingplanner.enums.EquipmentName;
import com.formation.meetingplanner.meeting.Meeting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private EquipmentName name;
    private Integer quantity;
    @ManyToMany(mappedBy = "equipmentList")
    @JsonIgnore
    private List<Meeting> meetingList;
}
