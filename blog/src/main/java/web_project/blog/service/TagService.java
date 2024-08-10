package web_project.blog.service;

import web_project.blog.model.dto.TagDTO;
import web_project.blog.model.entity.TagEntity;

import java.util.List;

public interface TagService {

    void add(TagDTO tagDTO);

    List<TagEntity> getAll();

    List<TagEntity> getByIds(List<Long> ids);
}
