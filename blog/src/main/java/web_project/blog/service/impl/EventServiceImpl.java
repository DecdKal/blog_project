package web_project.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClient;
import web_project.blog.model.dto.AddEventDTO;
import web_project.blog.model.dto.EventSummaryDTO;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.service.EventService;
import web_project.blog.service.UserService;
import web_project.blog.service.exception.ApiObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);
    private final RestClient eventRestClient;
    private final UserService userService;

    public EventServiceImpl(@Qualifier("eventsRestClient") RestClient eventRestClient, UserService userService) {
        this.eventRestClient = eventRestClient;
        this.userService = userService;
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ApiObjectNotFoundException.class)
//    @ResponseBody
//    public NotFoundErrorInfo handleApiObjectNotFoundException(ApiObjectNotFoundException apiObjectNotFoundException) {
//        return new NotFoundErrorInfo("NOT FOUND", apiObjectNotFoundException.getId());
//    }

    @Override
    public EventSummaryDTO getEventDetails(Long id) {

        return eventRestClient
                .get()
                .uri("/events/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(EventSummaryDTO.class);
    }

    @Override
    public void createEvent(AddEventDTO addEventDTO) {
        LOGGER.debug("Creating new event...");
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(pUserDetails -> addEventDTO.setOrganizerEmail(pUserDetails.getUsername()));

        eventRestClient
                .post()
                .uri("/events")
                .accept(MediaType.APPLICATION_JSON)
                .body(addEventDTO)
                .retrieve();
    }

    @Override
    public List<EventSummaryDTO> getAllEventsSummary() {

        return eventRestClient
                .get()
                .uri("/events")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Override
    public void deleteEvent(Long id) {
        eventRestClient
                .delete()
                .uri("/events/" + id)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void patchEvent(EventSummaryDTO summaryDTO) {
        eventRestClient
                .patch()
                .uri("/events/" + summaryDTO.id())
                .accept(MediaType.APPLICATION_JSON)
                .body(summaryDTO)
                .retrieve();
    }


    public record NotFoundErrorInfo(String code, Object id) {

    }
}
