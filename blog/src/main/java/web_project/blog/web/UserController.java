package web_project.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @PatchMapping("/edit")
    public String editUsername(@ModelAttribute UserProfileDTO userProfileDTO, Model model) {
        Optional<PUserDetails> user = userService.getCurrentUser();
        user.ifPresent(pUserDetails -> {
            UserProfileDTO profileDTO = userService.getUserByEmailAndMapToDTO(pUserDetails.getUsername());
            model.addAttribute("user", profileDTO);
            userService.updateUser(userProfileDTO, pUserDetails);
        });

        return "redirect:/";
    }
}
