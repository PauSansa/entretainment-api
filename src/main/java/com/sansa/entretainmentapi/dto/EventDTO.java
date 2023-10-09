package com.sansa.entretainmentapi.dto;

import com.sansa.entretainmentapi.entity.EventCategory;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDTO {
    private String title;
    private LocalDate date;
    private String description;
    private EventCategory category;
}
