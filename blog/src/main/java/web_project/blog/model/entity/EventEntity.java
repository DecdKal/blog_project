package web_project.blog.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import web_project.blog.model.validation.DateNotInThePast;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventEntity extends BaseEntity{

    @NotEmpty
    @Email
    private String organizerEmail;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @DateNotInThePast
    private LocalDate date;

    @NotNull
    private LocalTime time;

    public LocalDate getDate() {
        return date;
    }

    public EventEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getTime() {
        return time;
    }

    public EventEntity setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public EventEntity setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
