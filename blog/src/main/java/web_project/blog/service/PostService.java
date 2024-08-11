package web_project.blog.service;

import web_project.blog.model.dto.AddPostDTO;
import web_project.blog.model.dto.PostSummaryDTO;
import web_project.blog.model.user.PUserDetails;

import java.util.List;

public interface PostService {

    void createPost(AddPostDTO addPostDTO, PUserDetails userDetails);

    List<PostSummaryDTO> getAllPosts();

    PostSummaryDTO getPostDetails(Long id);

    void deletePost(Long id);

    void updatePost(PostSummaryDTO postSummaryDTO, PUserDetails userDetails);
}
