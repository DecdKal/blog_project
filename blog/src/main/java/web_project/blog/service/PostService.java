package web_project.blog.service;

import web_project.blog.model.dto.AddPostDTO;
import web_project.blog.model.user.PUserDetails;

public interface PostService {

    void createPost(AddPostDTO addPostDTO, PUserDetails userDetails);
}
