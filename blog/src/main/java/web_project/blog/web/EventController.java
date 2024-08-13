package web_project.blog.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web_project.blog.model.dto.AddEventDTO;
import web_project.blog.model.dto.CategoryDTO;
import web_project.blog.model.dto.EventSummaryDTO;
import web_project.blog.service.EventService;
import web_project.blog.service.exception.ObjectNotFoundException;

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
        //return "events";
        throw new NullPointerException();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("event-not-found");
        modelAndView.addObject("eventId", onfe.getId());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public String eventDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("event", eventService.getEventDetails(id));
        return "event-details";
    }

    @GetMapping("/add")
    public String addEvent(Model model) {
        if (!model.containsAttribute("addEventDTO")) {
            model.addAttribute("addEventDTO", AddEventDTO.empty());
        }
        return "event-add";
    }

    @PostMapping("/add")
    public String addEvent(@Valid AddEventDTO addEventDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addEventDTO", addEventDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addEventDTO", bindingResult);
            return "redirect:/events/add";
        }
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
