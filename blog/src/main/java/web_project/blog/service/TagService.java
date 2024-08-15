package web_project.blog.service;

import web_project.blog.model.dto.TagDTO;
import web_project.blog.model.entity.TagEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TagService {

    void add(TagDTO tagDTO);

    List<TagEntity> getAll();

    List<TagEntity> getByIds(List<Long> ids);
}
