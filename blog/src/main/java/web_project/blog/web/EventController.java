package web_project.blog.web;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web_project.blog.model.dto.AddEventDTO;
import web_project.blog.model.dto.EventSummaryDTO;
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

    @DeleteMapping(path = "/{id}")
    @Transactional
    public String deleteEvent(@PathVariable("id") Long id) {

        eventService.deleteEvent(id);

        return "redirect:/events/all";
    }

    @GetMapping("/update/{id}")
    public String updateEventForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("event", eventService.getEventDetails(id));

        return "event-update";
    }

    @PatchMapping(path = "/update/{id}")
    @Transactional
    public String patchEvent(@PathVariable("id") Long id, @ModelAttribute EventSummaryDTO event) {

        eventService.patchEvent(event);

        return "redirect:/events/all";
    }
}
