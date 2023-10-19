package com.sansa.entretainmentapi.controller;

import com.sansa.entretainmentapi.dto.EventDTO;
import com.sansa.entretainmentapi.entity.Event;
import com.sansa.entretainmentapi.model.ErrorResponse;
import com.sansa.entretainmentapi.model.UpdateResponse;
import com.sansa.entretainmentapi.service.EventService;
import com.sansa.entretainmentapi.util.UUIDChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final UUIDChecker uuidChecker;

    @GetMapping("/random")
    public Mono<ResponseEntity<Event>> getRandomEvent() {
        return eventService.getRandomEvent().map(ResponseEntity::ok);
    }

    @GetMapping()
    public ResponseEntity<Flux<?>> getAllEvents(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        if(offset == null && limit == null) {
            return ResponseEntity.ok(eventService.findAllEvents());
        }
        if( offset == null ^ limit == null){
            return ResponseEntity.badRequest().body(Flux.just(new ErrorResponse("Both offset and limit must be provided")));
        } else {

            return ResponseEntity.ok(eventService.findAllPageable(offset, limit));
        }
    }

    @GetMapping("/{uuid}")
    public Mono<ResponseEntity<Event>> getEventById(@PathVariable String uuid) {
        if(uuidChecker.isUUID(uuid)){
            return eventService.findOneEvent(UUID.fromString(uuid))
                    .map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        } else {
            return Mono.just(ResponseEntity.badRequest().build());
        }
    }


    @DeleteMapping("/{uuid}")
    public Mono<ResponseEntity<?>> deleteEvent(@PathVariable String uuid) {
        if(uuidChecker.isUUID(uuid)){
            return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).body(eventService.deleteByUUID(UUID.fromString(uuid))));
        } else {
            return Mono.just(ResponseEntity.badRequest().body(new ErrorResponse("Invalid UUID Provided")));
        }
    }

    @PutMapping("/{uuid}")
    public Mono<ResponseEntity<?>> updateEvent(@RequestBody EventDTO eventDTO, @PathVariable String uuid) {
        if(uuidChecker.isUUID(uuid)){
            Mono<Event> updated = eventService.updateEvent(UUID.fromString(uuid), eventDTO);
            return Mono.just(ResponseEntity.ok(new UpdateResponse<Mono<Event>>("Event Updated", updated)));
        } else {
            return Mono.just(ResponseEntity.badRequest().body(new ErrorResponse("Invalid UUID Provided")));
        }
    }

}
