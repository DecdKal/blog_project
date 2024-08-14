package web_project.blog.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import web_project.blog.model.validation.UniqueEmail;
import web_project.blog.model.validation.UniqueUsername;

public class RegistrationDTO {

    @UniqueUsername
    @NotEmpty(message = "Username cannot be empty.")
    private String username;

    @UniqueEmail
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Enter a valid email address.")
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    private String password;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public RegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public static RegistrationDTO empty() {
        return new RegistrationDTO(null, null, null);
    }
}
