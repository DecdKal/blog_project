package web_project.blog.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class TagDTO {

    @NotEmpty(message = "Enter a Tag name.")
    private String name;

    public TagDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TagDTO setName(String name) {
        this.name = name;
        return this;
    }

    public static TagDTO empty() {
        return new TagDTO(null);
    }
}
