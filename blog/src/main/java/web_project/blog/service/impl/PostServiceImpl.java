package web_project.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import web_project.blog.model.dto.AddPostDTO;
import web_project.blog.model.dto.PostSummaryDTO;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.PostEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.repository.CategoryRepository;
import web_project.blog.repository.PostRepository;
import web_project.blog.repository.TagRepository;
import web_project.blog.service.CategoryService;
import web_project.blog.service.PostService;
import web_project.blog.service.TagService;
import web_project.blog.service.UserService;
import web_project.blog.service.exception.ObjectNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserService userService, TagRepository tagRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createPost(AddPostDTO addPostDTO, PUserDetails userDetails) {

        this.postRepository.save(map(addPostDTO, userDetails));
    }

    @Override
    public List<PostSummaryDTO> getAllPosts() {
        return postRepository.findAll().stream().map(p -> modelMapper.map(p, PostSummaryDTO.class)).toList();
    }

    @Override
    public PostSummaryDTO getPostDetails(Long id) {
        //return modelMapper.map(postRepository.findById(id), PostSummaryDTO.class);

        return this.postRepository
                .findById(id)
                .map(this::mapToSummary)
                .orElseThrow(() -> new ObjectNotFoundException("Event not found!", id));
    }

    @Override
    public void deletePost(Long id) {
        Optional<PostEntity> post = postRepository.findById(id);

        post.ifPresent(postRepository::delete);
    }

    @Override
    public void updatePost(PostSummaryDTO postSummaryDTO, PUserDetails userDetails) {
        PostEntity updatedPost = map(postSummaryDTO);

        if(updatedPost != null) {
            this.postRepository.save(updatedPost);
        }
    }

    private PostEntity map(AddPostDTO addPostDTO, PUserDetails user) {
        PostEntity post = modelMapper.map(addPostDTO, PostEntity.class);
        Optional<UserEntity> userEntity = userService.getUserByEmail(user.getUsername());

        if (userEntity.isPresent()) {
            post.setCreatedOn(LocalDateTime.now());
            post.setLastUpdatedOn(LocalDateTime.now());
            post.setAuthor(userEntity.get());

            List<TagEntity> tags = new ArrayList<>();
            List<CategoryEntity> categories = new ArrayList<>();

            for (Long tag : addPostDTO.getTags()) {
                Optional<TagEntity> realTag = tagRepository.findById(tag);
                realTag.ifPresent(tags::add);
            }
            for (Long category : addPostDTO.getCategories()) {
                Optional<CategoryEntity> realTag = categoryRepository.findById(category);
                realTag.ifPresent(categories::add);
            }

            post.setCategories(categories);
            post.setTags(tags);
        }
        return post;
    }

    private PostEntity map(PostSummaryDTO postSummaryDTO) {
        Optional<PostEntity> post = postRepository.findById(postSummaryDTO.getId());

        if(post.isPresent()) {
            PostEntity updatedPost = post.get();
            updatedPost.setTitle(postSummaryDTO.getTitle());
            updatedPost.setContent(postSummaryDTO.getContent());
            updatedPost.setTags(postSummaryDTO.getTags());
            updatedPost.setCategories(postSummaryDTO.getCategories());
            updatedPost.setLastUpdatedOn(LocalDateTime.now());
            return updatedPost;
        }
        return null;
    }

    private PostSummaryDTO mapToSummary(PostEntity post) {
        return modelMapper.map(post, PostSummaryDTO.class);
    }
}
