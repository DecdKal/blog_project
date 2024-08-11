package web_project.blog.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AddEventDTO {
    private String organizerEmail;

    private String name;

    private String description;

    private LocalDate date;

    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public AddEventDTO setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public AddEventDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public AddEventDTO setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddEventDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddEventDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
