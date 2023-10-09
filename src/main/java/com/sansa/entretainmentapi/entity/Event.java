package com.sansa.entretainmentapi.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "event")
@Data
@Builder
public class Event {
    @Field("_id")
    @MongoId
    private UUID uuid;
    private String title;
    private LocalDate date;
    private String description;
    private EventCategory category;
}
