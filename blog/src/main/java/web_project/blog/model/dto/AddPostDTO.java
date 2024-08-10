package web_project.blog.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.CommentEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class AddPostDTO {

    private String title;

    private String content;

    private UserEntity author;

    private LocalDateTime createdOn;

    private LocalDateTime lastUpdatedOn;

    private List<CommentEntity> comments;

    private List<TagEntity> tags;

    private List<CategoryEntity> categories;

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

    public List<CommentEntity> getComments() {
        return comments;
    }

    public AddPostDTO setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public AddPostDTO setTags(List<TagEntity> tags) {
        this.tags = tags;
        return this;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public AddPostDTO setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }
}
