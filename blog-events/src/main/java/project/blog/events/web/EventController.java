package project.blog.events.web;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.blog.events.model.dto.AddEventDTO;
import project.blog.events.model.dto.EventDTO;
import project.blog.events.repository.EventRepository;
import project.blog.events.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping()
    public ResponseEntity<AddEventDTO> createEvent(@RequestBody AddEventDTO addEventDTO) {
        LOGGER.info("Going to create an Event {}", addEventDTO);

        eventService.createEvent(addEventDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDTO> deleteById(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);

        return ResponseEntity.ok().build();
    }
}
