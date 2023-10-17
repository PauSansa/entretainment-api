package com.sansa.entretainmentapi.service;

import com.sansa.entretainmentapi.dto.EventDTO;
import com.sansa.entretainmentapi.entity.Event;
import com.sansa.entretainmentapi.repository.EventRepository;
import com.sansa.entretainmentapi.util.OffsetBasedPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Mono<Event> getRandomEvent() {
        return eventRepository.getRandomEvent();
    }

    public Mono<Event> findOneEvent(UUID uuid) {
            return eventRepository.findById(uuid);
    }

    public Mono<Void> deleteByUUID(UUID uuid) {
        return eventRepository.deleteById(uuid);
    }

    public Flux<Event> findAllEvents() {
        return eventRepository.findAll();
    }
    public Flux<Event> findAllPageable(int offset, int limit) {
        Pageable pageable = new OffsetBasedPageRequest(offset, limit);
        return eventRepository.retrieveAllEventsPaged(pageable);
    }

    public Mono<Event> updateEvent(UUID uuid, EventDTO eventDTO) {
        return eventRepository.findById(uuid)
                .flatMap(existingEvent -> {
                    existingEvent.setTitle(eventDTO.getTitle());
                    existingEvent.setDate(eventDTO.getDate());
                    existingEvent.setCategory(eventDTO.getCategory());
                    existingEvent.setDescription(eventDTO.getDescription());
                    return eventRepository.save(existingEvent);
                });
    }
}
