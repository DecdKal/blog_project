package web_project.blog.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.repository.TagRepository;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "pesho@example.com",
        roles = {"USER", "ADMIN"}
)
public class TagControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void testTagCreation() throws Exception {
        mockMvc.perform(post("/tags/add")
                        .param("name", "TestTag")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Optional<TagEntity> tagEntityOptional = tagRepository.findByName("TestTag");

        Assertions.assertTrue(tagEntityOptional.isPresent());
    }
}