package web_project.blog.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tags")

public class TagEntity  extends BaseEntity{

    @NotEmpty
    private String name;


    public String getName() {
        return name;
    }

    public TagEntity setName(String name) {
        this.name = name;
        return this;
    }
}
