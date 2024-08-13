package project.blog.events.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.blog.events.model.dto.AddEventDTO;
import project.blog.events.model.dto.EventDTO;
import project.blog.events.model.entity.EventEntity;
import project.blog.events.repository.EventRepository;
import project.blog.events.service.EventService;
import project.blog.events.service.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
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
    public EventDTO createEvent(AddEventDTO addEventDTO) {
        EventEntity eventEntity = eventRepository.save(map(addEventDTO));

        return modelMapper.map(eventEntity, EventDTO.class);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository
                .findAll()
                .stream()
                .map(event ->
                        modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public EventDTO patchEvent(EventDTO eventDTO) {
        Optional<EventEntity> dbEntity = eventRepository.findById(eventDTO.getId());

        if(dbEntity.isPresent()) {
            EventEntity updatedEntity = dbEntity.get();
            updatedEntity.setName(eventDTO.getName());
            updatedEntity.setDescription(eventDTO.getDescription());
            eventRepository.save(updatedEntity);
        }
        return eventDTO;
    }

    @Override
    public EventDTO getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .map(eventDTO -> modelMapper.map(eventDTO, EventDTO.class))
                .orElseThrow(ObjectNotFoundException::new);
    }

    private EventEntity map(AddEventDTO addEventDTO) {
        return modelMapper.map(addEventDTO, EventEntity.class);
    }
}
