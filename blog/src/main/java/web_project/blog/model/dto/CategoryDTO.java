package web_project.blog.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

    @NotEmpty(message = "Name must not be empty.")
    private String name;

    @NotEmpty(message = "Description must not be empty.")
    @Size(min = 5, message = "Description must be longer than 5 characters.")
    private String description;

    public CategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public CategoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public static CategoryDTO empty() {
        return new CategoryDTO(null, null);
    }
}
