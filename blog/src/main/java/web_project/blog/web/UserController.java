package web_project.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import web_project.blog.model.dto.UserProfileDTO;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getProfile(@ModelAttribute UserProfileDTO userProfileDTO, Model model) {
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(pUserDetails -> model.addAttribute("user", userService.getUserByEmailAndMapToDTO(pUserDetails.getUsername())));

        return "profile";
    }
}