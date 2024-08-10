package web_project.blog.service;

import web_project.blog.model.dto.AddEventDTO;
import web_project.blog.model.dto.EventSummaryDTO;

import java.util.List;

public interface EventService {

    void createEvent(AddEventDTO addEventDTO);

    public List<EventSummaryDTO> getAllEventsSummary();

    public EventSummaryDTO getEventDetails(Long id);

    void deleteEvent(Long id);

    void patchEvent(EventSummaryDTO summaryDTO);
}
