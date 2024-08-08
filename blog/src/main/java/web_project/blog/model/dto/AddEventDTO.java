package web_project.blog.model.dto;

public class AddEventDTO {
    private String organizerEmail;

    private String name;

    private String description;

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
