package com.formation.meetingplanner.meeting;

import com.formation.meetingplanner.RoomEquipment.RoomEquipment;
import com.formation.meetingplanner.dtos.MeetingDto;
import com.formation.meetingplanner.enums.EquipmentName;
import com.formation.meetingplanner.enums.MeetingType;
import com.formation.meetingplanner.equipment.Equipment;
import com.formation.meetingplanner.equipment.EquipmentRepository;
import com.formation.meetingplanner.room.Room;
import com.formation.meetingplanner.room.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final RoomRepository roomRepository;
    private  final EquipmentRepository equipmentRepository;

    public MeetingService(MeetingRepository meetingRepository, RoomRepository roomRepository, EquipmentRepository equipmentRepository) {
        this.meetingRepository = meetingRepository;
        this.roomRepository = roomRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public List<Meeting> getMeetings() {
        return meetingRepository.findAll();
    }
    public void deleteMeeting(UUID meetingId) {
        meetingRepository.deleteById(meetingId);
    }

    public Room getRoomBasedOnCapacity(List<Room> rooms,Integer nbrPerson) {
        Room smallestRoom = null;

        for(Room room:rooms){
            if((room.getCapacity() * 0.7) >= nbrPerson ){
                if(smallestRoom == null || smallestRoom.getCapacity() > room.getCapacity()){
                    smallestRoom = room;
                }
            }
        }

        return smallestRoom;
    }

    public List<EquipmentName> findMissingEquipment(List<RoomEquipment> equipments, MeetingType type) {
        if (type.equals(MeetingType.RS)) {
            return List.of();
        }

        HashMap<MeetingType, List<EquipmentName>> requiredEquipmentMap = new HashMap<>();
        requiredEquipmentMap.put(MeetingType.VC, List.of(EquipmentName.ECRAN, EquipmentName.WEBCAM, EquipmentName.PIEUVRE));
        requiredEquipmentMap.put(MeetingType.RC, List.of(EquipmentName.TABLEAU, EquipmentName.ECRAN, EquipmentName.PIEUVRE));
        requiredEquipmentMap.put(MeetingType.SPEC, List.of(EquipmentName.TABLEAU));

        List<EquipmentName> requiredEquipment = requiredEquipmentMap.get(type);

        return requiredEquipment.stream()
                .filter(equipment -> equipments.stream().noneMatch(roomEquipment -> roomEquipment.getName() == equipment))
                .collect(Collectors.toList());
    }

    public boolean hasTimeOverlap(List<Meeting> meetings, Meeting newMeeting) {
        for (Meeting existingMeeting : meetings) {
            if (existingMeeting.getDate().equals(newMeeting.getDate())
                    && (
                            existingMeeting.getStartTime().equals(newMeeting.getStartTime())
                                    ||Math.abs(Duration.between(existingMeeting.getEndTime(),newMeeting.getStartTime()).toHours()) < 1
                                    ||Math.abs(Duration.between(existingMeeting.getStartTime(),newMeeting.getEndTime()).toHours()) < 1
                     )
            ) {
                return true;
            }
        }
        return false;
    }

    public Meeting assignMeetingToBestRoom(List<Room> rooms,Meeting meeting) throws Exception{

        if(rooms.isEmpty()){
            throw new Exception("no room available");
        }

        Room room = getRoomBasedOnCapacity(rooms,meeting.getNbrPeople());

        List<EquipmentName> missingEquipment = findMissingEquipment(room.getEquipmentList(),meeting.getType());
        List<Equipment> equipments =  equipmentRepository.findByNameIn(missingEquipment);

        if(room.getMeetingList().isEmpty()){
            meeting.setEquipmentList(equipments);
            meeting.setRoom(room);
            return meeting;
        }

        List<Meeting> meetingList = room.getMeetingList();
        boolean isOverlap = hasTimeOverlap(meetingList,meeting);

        if (isOverlap) {
            List<Room> remainingRooms = new ArrayList<>(rooms);
            remainingRooms.remove(room);
            return assignMeetingToBestRoom(remainingRooms, meeting);
        }

        meeting.setEquipmentList(equipments);
        meeting.setRoom(room);
        return  meeting;
    }

    private MeetingDto mapToMeetingDto(Meeting meeting){
        String message = "You got the room : " +
                meeting.getRoom().getName();
        return MeetingDto.builder()
                .date(meeting.getDate())
                .endTime(meeting.getEndTime())
                .id(meeting.getId())
                .startTime(meeting.getStartTime())
                .nbrPeople(meeting.getNbrPeople())
                .type(meeting.getType())
                .room(meeting.getRoom())
                .message(message)
                .equipmentList(meeting.getEquipmentList())
                .build();
    }

    public MeetingDto addMeeting(Meeting newMeeting) {
        List<Room> rooms = roomRepository.findAll();

        try {
            Meeting meeting = assignMeetingToBestRoom(rooms, newMeeting);
            meetingRepository.save(meeting);
            return mapToMeetingDto(meeting);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No room available for the meeting", e);
        }


    }
}
