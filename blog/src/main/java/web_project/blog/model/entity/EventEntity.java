package web_project.blog.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizerEmail;

    private String name;

    private String description;

    private LocalDate date;

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

    public Long getId() {
        return id;
    }

    public EventEntity setId(Long id) {
        this.id = id;
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
