package web_project.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web_project.blog.model.dto.RegistrationDTO;
import web_project.blog.model.dto.TagDTO;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.repository.TagRepository;
import web_project.blog.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public TagServiceImpl(TagRepository tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(TagDTO tagDTO) {
        tagRepository.save(map(tagDTO));
    }

    @Override
    public List<TagEntity> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<TagEntity> getByIds(List<Long> ids) {
        return tagRepository.findByIdIn(ids);
    }

    private TagEntity map(TagDTO tagDTO) {
        return modelMapper.map(tagDTO, TagEntity.class);
    }
}
