package web_project.blog.model.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class PostSummaryDTO {

    private Long id;

    private String title;

    private String content;

    private UserEntity author;

    private LocalDateTime createdOn;

    private List<TagEntity> tags;

    private List<CategoryEntity> categories;

    public Long getId() {
        return id;
    }

    public PostSummaryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PostSummaryDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostSummaryDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PostSummaryDTO setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public PostSummaryDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public PostSummaryDTO setTags(List<TagEntity> tags) {
        this.tags = tags;
        return this;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public PostSummaryDTO setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }
}
