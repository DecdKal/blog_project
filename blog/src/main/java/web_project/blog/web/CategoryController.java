package web_project.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web_project.blog.model.dto.CategoryDTO;
import web_project.blog.model.dto.TagDTO;
import web_project.blog.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String addCategory() {
        return "category-add";
    }

    @PostMapping("/add")
    public String addCategory(CategoryDTO categoryDTO) {
        categoryService.add(categoryDTO);

        return "redirect:/";
    }
}
