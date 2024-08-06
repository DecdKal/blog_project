package web_project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web_project.blog.model.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
