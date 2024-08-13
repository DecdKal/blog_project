package web_project.blog.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String addCategory(Model model) {
        if (!model.containsAttribute("categoryDTO")) {
            model.addAttribute("categoryDTO", CategoryDTO.empty());
        }
        return "category-add";
    }

    @PostMapping("/add")
    public String addCategory( @Valid CategoryDTO categoryDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("categoryDTO", categoryDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.categoryDTO", bindingResult);
            return "redirect:/categories/add";
        }

        categoryService.add(categoryDTO);
        return "redirect:/";
    }
}
