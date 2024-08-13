package project.blog.events.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.blog.events.model.dto.AddEventDTO;
import project.blog.events.model.dto.EventDTO;
import project.blog.events.repository.EventRepository;
import project.blog.events.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Event management controller.")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(@AuthenticationPrincipal UserDetails userDetails) {

        if(userDetails != null) {
            System.out.println("User: " + userDetails.getUsername());
            System.out.println("Roles: " + userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));
        } else {
            System.out.println("NO CURRENT USER");
        }

        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Event details",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = EventDTO.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "404", description = "Event not found.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    })
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable("id") Long id) {
            return ResponseEntity.ok(eventService.getEventById(id));
    }

    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-key"
            )
    )
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody AddEventDTO addEventDTO) {
        LOGGER.info("Going to create an Event {}", addEventDTO);

        EventDTO eventDTO = eventService.createEvent(addEventDTO);
        return ResponseEntity.
                created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(eventDTO.getId())
                                .toUri()
                ).body(eventDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDTO> deleteById(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventDTO> patchEvent (@PathVariable("id") Long id, @RequestBody EventDTO eventDTO) {
        eventService.patchEvent(eventDTO);

        return ResponseEntity.ok().build();
    }

}
