package web_project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web_project.blog.model.entity.UserRoleEntity;
import web_project.blog.model.enums.UserRoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity getUserRoleEntityByName(UserRoleEnum name);

}
