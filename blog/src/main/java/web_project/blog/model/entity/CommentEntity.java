package web_project.blog.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PostEntity post;

    @ManyToOne
    private UserEntity author;

    private String content;

    private LocalDateTime postedOn;

    public Long getId() {
        return id;
    }

    public CommentEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PostEntity getPost() {
        return post;
    }

    public CommentEntity setPost(PostEntity post) {
        this.post = post;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public CommentEntity setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
        return this;
    }

}
