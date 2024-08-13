package web_project.blog.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web_project.blog.model.dto.CategoryDTO;
import web_project.blog.model.dto.RegistrationDTO;
import web_project.blog.service.UserService;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("registerDTO")) {
            model.addAttribute("registerDTO", RegistrationDTO.empty());
        }
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationDTO registerDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("registerDTO", registerDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            return "redirect:/users/register";
        }

        userService.registerUser(registerDTO);

        return "redirect:/";
    }
}
