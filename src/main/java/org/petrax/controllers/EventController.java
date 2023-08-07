package org.petrax.controllers;

import org.petrax.data.EventRepository;
import org.petrax.models.Event;
import org.petrax.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;   // Use the service instead of the repository directly

    @Autowired
    public EventController(EventService eventService) {  // Constructor injection for service
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();  // Utilize the service method here
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Optional<Event> event = eventService.getEventById(id);  // Utilize the service method here
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event savedEvent = eventService.addEvent(event);   // Utilize the service method here
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event updatedEvent) {
        Optional<Event> event = eventService.updateEvent(id, updatedEvent);  // Utilize the service method here
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);  // Utilize the service method here
    }
}




//@RestController
//@RequestMapping("/api/events")
//public class EventController {
//    private final EventRepository eventRepository;
//
//    @Autowired
//    public EventController(EventRepository eventRepository) {
//        this.eventRepository = eventRepository;
//    }
//
//    @GetMapping
//    public List<Event> getAllEvents() {
//        return eventRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Event> getEventById(@PathVariable int id) {
//        Optional<Event> event = eventRepository.findById(id);
//        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping("/api/events")
//    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
//        Event savedEvent = eventService.addEvent(event);
//        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
//    }
//
//
////    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
////    public Event createEvent(@RequestBody Event event) {
////        return eventRepository.save(event);
////    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event updatedEvent) {
//        Optional<Event> event = eventRepository.findById(id);
//        if (event.isPresent()) {
//            updatedEvent.setId(id);
//            return ResponseEntity.ok(eventRepository.save(updatedEvent));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteEvent(@PathVariable int id) {
//        eventRepository.deleteById(id);
//    }
//}
