package web_project.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import web_project.blog.model.dto.AddEventDTO;
import web_project.blog.service.EventService;

@Service
public class EventServiceImpl implements EventService {

    private Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);
    private final RestClient eventRestClient;

    public EventServiceImpl(@Qualifier("eventsRestClient") RestClient eventRestClient) {
        this.eventRestClient = eventRestClient;
    }

    @Override
    public void createEvent(AddEventDTO addEventDTO) {
        LOGGER.debug("Creating new event...");

        eventRestClient
                .post()
                .uri("http://localhost:8081/events")
                .accept(MediaType.APPLICATION_JSON)
                .body(addEventDTO)
                .retrieve();
    }
}
