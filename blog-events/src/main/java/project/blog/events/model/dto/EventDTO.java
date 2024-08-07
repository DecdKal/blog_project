package project.blog.events.model.dto;

public class EventDTO {
    private String organizerEmail;

    private String name;

    private String description;

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
