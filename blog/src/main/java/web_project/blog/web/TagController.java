package web_project.blog.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web_project.blog.model.dto.TagDTO;
import web_project.blog.service.TagService;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/add")
    public String addTag(Model model) {
        if (!model.containsAttribute("tagDTO")) {
            model.addAttribute("tagDTO", TagDTO.empty());
        }
        return "tag-add";
    }

    @PostMapping("/add")
    public String addTag(@Valid TagDTO tagDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("tagDTO", tagDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.tagDTO", bindingResult);
            return "redirect:/tags/add";
        }
        tagService.add(tagDTO);
        return "redirect:/";
    }
}
