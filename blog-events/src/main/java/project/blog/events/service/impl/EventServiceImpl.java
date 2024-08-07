package project.blog.events.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.blog.events.model.dto.AddEventDTO;
import project.blog.events.model.entity.EventEntity;
import project.blog.events.repository.EventRepository;
import project.blog.events.service.EventService;

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

    private EventEntity map(AddEventDTO addEventDTO) {
        return modelMapper.map(addEventDTO, EventEntity.class);
    }
}
