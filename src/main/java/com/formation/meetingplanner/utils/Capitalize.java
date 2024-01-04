package com.formation.meetingplanner.utils;
import org.springframework.stereotype.Component;

@Component
public class Capitalize {
    public String capitalize(String string){
        return string.toUpperCase().charAt(0) +
                string.toLowerCase().substring(1);
    }
}
