package project.blog.events.service;

import project.blog.events.model.dto.AddEventDTO;

public interface EventService {

    void createEvent(AddEventDTO addEventDTO);
}
