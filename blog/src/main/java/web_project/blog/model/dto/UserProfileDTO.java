package web_project.blog.model.dto;

public class UserProfileDTO {

    private String username;

    private String email;

    private String description;

    public String getUsername() {
        return username;
    }

    public UserProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserProfileDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
