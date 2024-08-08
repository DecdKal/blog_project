package web_project.blog.service;

import web_project.blog.model.dto.AddEventDTO;

public interface EventService {

    void createEvent(AddEventDTO addEventDTO);
}
