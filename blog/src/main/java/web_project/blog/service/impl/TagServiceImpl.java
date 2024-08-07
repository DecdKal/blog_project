package web_project.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web_project.blog.model.dto.RegistrationDTO;
import web_project.blog.model.dto.TagDTO;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.repository.TagRepository;
import web_project.blog.service.TagService;

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

    private TagEntity map(TagDTO tagDTO) {
        return modelMapper.map(tagDTO, TagEntity.class);
    }
}
