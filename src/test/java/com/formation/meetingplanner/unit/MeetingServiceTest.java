package com.formation.meetingplanner.unit;

import com.formation.meetingplanner.RoomEquipment.RoomEquipment;
import com.formation.meetingplanner.enums.EquipmentName;
import com.formation.meetingplanner.enums.MeetingType;
import com.formation.meetingplanner.equipment.EquipmentRepository;
import com.formation.meetingplanner.meeting.Meeting;
import com.formation.meetingplanner.meeting.MeetingRepository;
import com.formation.meetingplanner.meeting.MeetingService;
import com.formation.meetingplanner.room.Room;
import com.formation.meetingplanner.room.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class MeetingServiceTest {
    @Mock
    RoomRepository roomRepository;
    @Mock
    MeetingRepository meetingRepository;
    @Mock
    EquipmentRepository equipmentRepository;

    MeetingService meetingService= new MeetingService(meetingRepository,roomRepository,equipmentRepository);
    public List<Room> createMockRooms() {
        return List.of(
                Room.builder()
                        .name("E1001")
                        .capacity(23)
                        .meetingList(List.of())
                        .equipmentList(
                                List.of(
                                        RoomEquipment.builder()
                                                .name(EquipmentName.valueOf("NEANT"))
                                                .build()
                                )
                        ).build(),
                Room.builder()
                        .name("E1002")
                        .capacity(10)
                        .meetingList(List.of())
                        .equipmentList(
                                List.of(
                                        RoomEquipment.builder()
                                                .name(EquipmentName.valueOf("ECRAN"))
                                                .build()
                                )
                        )
                        .build(),
                Room.builder()
                        .name("E2002")
                        .capacity(15)
                        .meetingList(List.of())
                        .equipmentList(
                                List.of(
                                        RoomEquipment.builder()
                                                .name(EquipmentName.valueOf("ECRAN"))
                                                .build(),
                                        RoomEquipment.builder()
                                                .name(EquipmentName.valueOf("WEBCAM"))
                                                .build()
                                )
                        )
                        .build()
        );
    }


    @Test
    void itShouldReturnRoomBasedOnCapacity(){
        // Given
        Integer nbrPerson = 7;
        Integer nbrPersonTwo = 10;
        Integer nbrPersonThree = 16;

        // When
        Room room = meetingService.getRoomBasedOnCapacity(createMockRooms(),nbrPerson);
        Room roomTwo = meetingService.getRoomBasedOnCapacity(createMockRooms(),nbrPersonTwo);
        Room roomThree = meetingService.getRoomBasedOnCapacity(createMockRooms(),nbrPersonThree);

        // Then
        Assertions.assertEquals(createMockRooms().get(1), room);
        Assertions.assertEquals(createMockRooms().get(2), roomTwo);
        Assertions.assertEquals(createMockRooms().get(0), roomThree);

    }

    @Test
    void itShouldReturnMissingEquipment(){
        // Given
       List<RoomEquipment> equipments = createMockRooms().get(1).getEquipmentList();
       List<RoomEquipment> equipmentsTwo = createMockRooms().get(2).getEquipmentList();
       List<RoomEquipment> equipmentsThree = createMockRooms().get(0).getEquipmentList();

        // When
       List<EquipmentName> messingEquipments = meetingService.findMissingEquipment(equipments, MeetingType.RS);
       List<EquipmentName> messingEquipmentsTwo = meetingService.findMissingEquipment(equipmentsTwo, MeetingType.SPEC);
       List<EquipmentName> messingEquipmentsThree = meetingService.findMissingEquipment(equipmentsThree, MeetingType.RC);

        // Then
        Assertions.assertEquals(List.of(), messingEquipments);
        Assertions.assertEquals(List.of(EquipmentName.TABLEAU), messingEquipmentsTwo);
        Assertions.assertEquals(List.of(EquipmentName.TABLEAU,EquipmentName.ECRAN , EquipmentName.PIEUVRE), messingEquipmentsThree);
    }

    @Test
    void itShouldReturnTrueOnTimeOverlap(){
        Meeting existingMeeting1 = Meeting.builder()
                .date(LocalDate.parse("2022-01-02"))
                .startTime(LocalTime.parse("09:00:00"))
                .endTime(LocalTime.parse("10:00:00")).build();

        Meeting existingMeeting2 =  Meeting.builder()
                .date(LocalDate.parse("2022-01-02"))
                .startTime(LocalTime.parse("10:00:00"))
                .endTime(LocalTime.parse("11:00:00")).build();


        List<Meeting> meetings = Arrays.asList(existingMeeting1, existingMeeting2);

        Meeting newMeeting = Meeting.builder()
                .date(LocalDate.parse("2022-01-02"))
                .startTime(LocalTime.parse("11:00"))
                .endTime(LocalTime.parse("12:00")).build();
        Assertions.assertTrue(meetingService.hasTimeOverlap(meetings, newMeeting));

        log.info("_________");
        newMeeting.setStartTime(LocalTime.parse("12:00"));
        newMeeting.setEndTime(LocalTime.parse("13:00"));
        Assertions.assertFalse(meetingService.hasTimeOverlap(meetings, newMeeting));

        log.info("_________");
        newMeeting.setStartTime(LocalTime.parse("08:00"));
        newMeeting.setEndTime(LocalTime.parse("09:00"));
        Assertions.assertTrue(meetingService.hasTimeOverlap(meetings, newMeeting));

        log.info("_________");
        newMeeting.setStartTime(LocalTime.parse("10:00"));
        newMeeting.setEndTime(LocalTime.parse("11:00"));
        Assertions.assertTrue(meetingService.hasTimeOverlap(meetings, newMeeting));

        log.info("_________");
        newMeeting.setDate(LocalDate.parse("2022-01-01"));
        newMeeting.setStartTime(LocalTime.parse("11:00"));
        newMeeting.setEndTime(LocalTime.parse("12:00"));
        Assertions.assertFalse(meetingService.hasTimeOverlap(meetings, newMeeting));
    }

}
