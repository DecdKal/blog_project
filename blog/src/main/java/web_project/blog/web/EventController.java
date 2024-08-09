package web_project.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web_project.blog.model.dto.AddEventDTO;
import web_project.blog.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public String getAllEvents(Model model) {

        model.addAttribute("events", eventService.getAllEventsSummary());
        return "events";
    }

    @GetMapping("/{id}")
    public String eventDetails(@PathVariable("id") Long id,
                               Model model) {

        model.addAttribute("event", eventService.getEventDetails(id));

        return "event-details";
    }

    @GetMapping("/add")
    public String newEvent() {
        return "event-add";
    }

    @PostMapping("/add")
    public String addEvent(AddEventDTO addEventDTO) {
        eventService.createEvent(addEventDTO);

        return "redirect:/";
    }
}
