package web_project.blog.service;

import web_project.blog.model.dto.CategoryDTO;
import web_project.blog.model.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    void add(CategoryDTO categoryDTO);

    List<CategoryEntity> getAll();
}
