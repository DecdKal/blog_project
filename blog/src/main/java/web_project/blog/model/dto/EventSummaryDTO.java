package web_project.blog.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventSummaryDTO(Long id, String organizerEmail, String name, String description, LocalDate date, LocalTime time) {

    public String dateAndTime() {
        return "On " + date.toString() + " at " +time.toString();
    }
}
