package web_project.blog.model.entity;

import jakarta.persistence.*;
import web_project.blog.model.enums.UserRoleEnum;

@Table(name = "roles")
@Entity
public class UserRoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;

    public UserRoleEntity() {
    }

    public UserRoleEnum getRole() {
        return name;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.name = role;
        return this;
    }
}
