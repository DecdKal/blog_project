package web_project.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web_project.blog.model.dto.AddPostDTO;
import web_project.blog.model.dto.PostSummaryDTO;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.service.CategoryService;
import web_project.blog.service.PostService;
import web_project.blog.service.TagService;
import web_project.blog.service.UserService;

import java.util.ArrayList;
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
    public String getAllPosts(@ModelAttribute ArrayList<PostSummaryDTO> posts, Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    @GetMapping("/add")
    public String addPost(@ModelAttribute ArrayList<TagEntity> tags, @ModelAttribute ArrayList<CategoryEntity> categories, Model model) {
        model.addAttribute("post", new AddPostDTO());
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "post-add";
    }

    @GetMapping("/{id}")
    public String postDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.getPostDetails(id));
        return "post-details";
    }

    @GetMapping("/update/{id}")
    public String updatePost(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.getPostDetails(id));
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "post-update";
    }

    @PostMapping("/add")
    public String addPost(AddPostDTO addPostDTO) {
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(userDetails -> postService.createPost(addPostDTO, userDetails));

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(userDetails -> postService.deletePost(id));

        return "redirect:/posts/all";
    }

    @PatchMapping("/update/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute PostSummaryDTO post) {
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(userDetails -> postService.updatePost(post, userDetails));


        return "redirect:/posts/all";
    }
}
