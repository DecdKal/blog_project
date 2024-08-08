package project.blog.events.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.blog.events.model.dto.AddEventDTO;
import project.blog.events.model.dto.EventDTO;
import project.blog.events.model.entity.EventEntity;
import project.blog.events.repository.EventRepository;
import project.blog.events.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createEvent(AddEventDTO addEventDTO) {
        eventRepository.save(map(addEventDTO));
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(event -> modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public EventDTO getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .map(eventDTO -> modelMapper.map(eventDTO, EventDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("Such event does not exist."));

    }

    private EventEntity map(AddEventDTO addEventDTO) {
        return modelMapper.map(addEventDTO, EventEntity.class);
    }
}
