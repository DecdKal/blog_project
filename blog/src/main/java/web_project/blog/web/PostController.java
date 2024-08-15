package web_project.blog.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web_project.blog.model.dto.AddPostDTO;
import web_project.blog.model.dto.CategoryDTO;
import web_project.blog.model.dto.PostSummaryDTO;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.service.CategoryService;
import web_project.blog.service.PostService;
import web_project.blog.service.TagService;
import web_project.blog.service.UserService;
import web_project.blog.service.exception.ObjectNotFoundException;

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
        if (!model.containsAttribute("addPostDTO")) {
            model.addAttribute("addPostDTO", new AddPostDTO());
        }
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "post-add";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("post-not-found");
        modelAndView.addObject("postId", onfe.getId());

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleUnauthorized(IllegalAccessException iae) {
        ModelAndView modelAndView = new ModelAndView("unauthorized-action");

        return modelAndView;
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
    public String addPost(@Valid AddPostDTO addPostDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addPostDTO", addPostDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addPostDTO", bindingResult);
            return "redirect:/posts/add";
        }
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(userDetails -> postService.createPost(addPostDTO, userDetails));

        return "redirect:/posts/all";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") Long id) throws IllegalAccessException {
        Optional<PUserDetails> user = userService.getCurrentUser();

        if(user.isPresent() && !postService.getPostDetails(id).getAuthor().getEmail().equals(user.get().getUsername()) ) {
            throw new IllegalAccessException("You do not have permissions to do this action.");
        }
        user.ifPresent(userDetails ->postService.deletePost(id));

        return "redirect:/posts/all";
    }

    @PatchMapping("/update/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute PostSummaryDTO post) {
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(userDetails -> postService.updatePost(post, userDetails));

        return "redirect:/posts/all";
    }
}
