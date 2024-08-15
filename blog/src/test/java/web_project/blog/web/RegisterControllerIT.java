package web_project.blog.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.repository.UserRepository;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegistration() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("username", "stefan")
                        .param("email", "user@user.com")
                        .param("password", "1234")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Optional<UserEntity> userEntityOpt = userRepository.findByEmail("user@user.com");

        Assertions.assertTrue(userEntityOpt.isPresent());

        UserEntity userEntity = userEntityOpt.get();

        Assertions.assertEquals("stefan", userEntity.getUsername());

        Assertions.assertTrue(passwordEncoder.matches("1234", userEntity.getPassword()));
    }
}
