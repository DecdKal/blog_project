package web_project.blog.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import web_project.blog.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class AddPostDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    private UserEntity author;

    private LocalDateTime createdOn;

    private LocalDateTime lastUpdatedOn;

    private List<Long> tags;

    private List<Long> categories;

    public String getTitle() {
        return title;
    }

    public AddPostDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AddPostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public AddPostDTO setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public AddPostDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public AddPostDTO setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    public List<Long> getTags() {
        return tags;
    }

    public AddPostDTO setTags(List<Long> tags) {
        this.tags = tags;
        return this;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public AddPostDTO setCategories(List<Long> categories) {
        this.categories = categories;
        return this;
    }
}
