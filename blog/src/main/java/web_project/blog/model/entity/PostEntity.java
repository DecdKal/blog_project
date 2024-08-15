package web_project.blog.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
public class PostEntity  extends BaseEntity{

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    @ManyToOne
    private UserEntity author;

    private LocalDateTime createdOn;

    private LocalDateTime lastUpdatedOn;

    @ManyToMany
    private List<TagEntity> tags;

    @ManyToMany
    private List<CategoryEntity> categories;

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public PostEntity setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }


    public String getTitle() {
        return title;
    }

    public PostEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PostEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public PostEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public PostEntity setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public PostEntity setTags(List<TagEntity> tags) {
        this.tags = tags;
        return this;
    }
}
