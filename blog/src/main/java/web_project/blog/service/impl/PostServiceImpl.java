package web_project.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web_project.blog.model.dto.AddPostDTO;
import web_project.blog.model.entity.PostEntity;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.repository.PostRepository;
import web_project.blog.service.PostService;
import web_project.blog.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserService userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void createPost(AddPostDTO addPostDTO, PUserDetails userDetails) {

        this.postRepository.save(map(addPostDTO,userDetails));
    }

    private PostEntity map(AddPostDTO addPostDTO, PUserDetails user) {
        PostEntity post =  modelMapper.map(addPostDTO, PostEntity.class);
        Optional<UserEntity> userEntity = userService.getUserByEmail(user.getUsername());

        if(userEntity.isPresent()) {
            post.setCreatedOn(LocalDateTime.now());
            post.setLastUpdatedOn(LocalDateTime.now());
            post.setAuthor(userEntity.get());
        }
        return post;
    }
}
