package web_project.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String addTag() {
        return "tag-add";
    }

    @PostMapping("/add")
    public String addTag(TagDTO tagDTO) {
        tagService.add(tagDTO);

        return "redirect:/";
    }
}
