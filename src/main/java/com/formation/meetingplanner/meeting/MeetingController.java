package com.formation.meetingplanner.meeting;

import com.formation.meetingplanner.dtos.MeetingDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/meeting")
@CrossOrigin(origins = "http://127.0.0.1:4200/")
public class MeetingController {
    MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public List<Meeting> getMeetings(){
        return meetingService.getMeetings();
    }

    @PostMapping
    public MeetingDto addMeeting(@RequestBody Meeting meeting){
        return meetingService.addMeeting(meeting);
    }

    @DeleteMapping(path = "{meetingId}")
    public void deleteMeeting(@PathVariable("meetingId")UUID meetingId){
        meetingService.deleteMeeting(meetingId);
    }
}
