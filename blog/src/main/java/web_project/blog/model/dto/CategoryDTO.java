package web_project.blog.model.dto;

public class CategoryDTO {
    private String name;

    private String description;

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
}
