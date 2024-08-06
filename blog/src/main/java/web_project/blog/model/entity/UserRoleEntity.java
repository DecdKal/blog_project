package web_project.blog.model.entity;

import jakarta.persistence.*;
import web_project.blog.model.enums.UserRoleEnum;

@Table(name = "roles")
@Entity
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return name;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.name = role;
        return this;
    }
}
