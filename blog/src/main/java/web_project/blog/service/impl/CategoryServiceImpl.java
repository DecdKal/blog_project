package web_project.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web_project.blog.model.dto.CategoryDTO;
import web_project.blog.model.dto.TagDTO;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.repository.CategoryRepository;
import web_project.blog.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void add(CategoryDTO categoryDTO) {
        categoryRepository.save(map(categoryDTO));
    }

    private CategoryEntity map(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }
}
