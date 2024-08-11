package project.blog.events.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDTO {
        private Long id;

        private String organizerEmail;

        private String name;

        private String description;

        private LocalDate date;

        private LocalTime time;

    public LocalDate getDate() {
        return date;
    }

    public EventDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getTime() {
        return time;
    }

    public EventDTO setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public EventDTO() {}

    public Long getId() {
        return id;
    }

    public EventDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public EventDTO setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
