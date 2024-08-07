package project.blog.events.web;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.blog.events.model.dto.AddEventDTO;
import project.blog.events.repository.EventRepository;
import project.blog.events.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public ResponseEntity<AddEventDTO> createEvent(AddEventDTO addEventDTO) {
        eventService.createEvent(addEventDTO);
        return ResponseEntity.ok().build();
    }
}
