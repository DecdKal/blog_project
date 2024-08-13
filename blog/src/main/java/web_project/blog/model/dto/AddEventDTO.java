package web_project.blog.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import web_project.blog.model.validation.DateNotInThePast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AddEventDTO {
    private String organizerEmail;

    @NotEmpty(message = "Name must not be empty.")
    @Size(min = 5, message = "Name must be longer than 5 letters.")
    private String name;

    @NotEmpty(message = "Description must not be empty.")
    @Size(min = 5, message = "Description must be longer than 5 letters.")
    private String description;

    @DateNotInThePast
    @NotNull(message = "Please, specify the date of the event.")
    private LocalDate date;

    @NotNull(message = "Please, specify the time at which the event is taking place.")
    private LocalTime time;

    public AddEventDTO(String name, String description, LocalDate date, LocalTime time) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
    }

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

    public static AddEventDTO empty() {
        return new AddEventDTO(null, null, null, null);
    }
}
