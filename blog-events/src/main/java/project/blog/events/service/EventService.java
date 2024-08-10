package project.blog.events.service;

import project.blog.events.model.dto.AddEventDTO;
import project.blog.events.model.dto.EventDTO;

import java.util.List;

public interface EventService {

    EventDTO createEvent(AddEventDTO addEventDTO);

    List<EventDTO> getAllEvents();

    void deleteEvent(Long eventId);

    EventDTO patchEvent(EventDTO eventDTO);

    EventDTO getEventById(Long eventId);
}
