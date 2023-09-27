package com.sansa.entretainmentapi.repository;

import com.sansa.entretainmentapi.document.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventRepository extends ReactiveMongoRepository<Event, UUID> {

    @Aggregation(pipeline = {
            "{ $sample: { size: 1 } }"
    })
    Mono<Event> getRandomEvent();

    @Query("{ _id: { $exists: true }}")
    Flux<Event> retrieveAllEventsPaged(final Pageable page);
}
