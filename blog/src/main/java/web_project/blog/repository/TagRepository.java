package web_project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web_project.blog.model.entity.TagEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findByIdIn(List<Long> ids);

    Optional<TagEntity> findByName(String name);
}
