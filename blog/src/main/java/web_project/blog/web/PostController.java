package web_project.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web_project.blog.model.dto.AddPostDTO;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.PostEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.service.CategoryService;
import web_project.blog.service.PostService;
import web_project.blog.service.TagService;
import web_project.blog.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final TagService tagService;
    private final CategoryService categoryService;

    public PostController(PostService postService, UserService userService, TagService tagService, CategoryService categoryService) {
        this.postService = postService;
        this.userService = userService;
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String getAllPosts(Model model) {
        return "posts";
    }

    @GetMapping("/add")
    public String addPost(@ModelAttribute ArrayList<TagEntity> tags, @ModelAttribute ArrayList<CategoryEntity> categories, Model model) {
        model.addAttribute("post", new AddPostDTO());
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "post-add";
    }

    @PostMapping("/add")
    public String addPost(AddPostDTO addPostDTO) {
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(userDetails -> postService.createPost(addPostDTO, userDetails));

        return "redirect:/";
    }

}
