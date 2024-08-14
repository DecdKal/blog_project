package project.blog.events.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.blog.events.model.entity.EventEntity;
import project.blog.events.repository.EventRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerIT {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindById() throws Exception {
        var actualEntity = eventRepository.save(
                new EventEntity()
                        .setDescription("test")
                        .setName("test entity")
                        .setDate(LocalDate.of(2024, 12, 12))
                        .setTime(LocalTime.now())
                        .setOrganizerEmail("user@user.com")
        );

        ResultActions result = mockMvc.perform(get("/events/${id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

    }

}
