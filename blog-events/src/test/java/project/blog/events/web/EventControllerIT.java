package project.blog.events.web;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.blog.events.model.entity.EventEntity;
import project.blog.events.repository.EventRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "pesho@example.com",
        roles = {"USER", "ADMIN"}
)
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
                        .setTime(LocalTime.of(5, 30, 0))
                        .setOrganizerEmail("user@user.com")
        );

        mockMvc.perform(get("/events/{id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(actualEntity.getId().intValue())))
                .andExpect(jsonPath("$.description", is(actualEntity.getDescription())))
                .andExpect(jsonPath("$.name", is(actualEntity.getName())))
                .andExpect(jsonPath("$.organizerEmail", is(actualEntity.getOrganizerEmail())))
                .andExpect(jsonPath("$.date", is(actualEntity.getDate().toString())))
                .andExpect(jsonPath("$.time", is(actualEntity.getTime() + ":00")));

    }

    @Test
    public void testEventNotFound() throws Exception {
        mockMvc.perform(get("/events/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateEvent() throws Exception {
        MvcResult result = mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                  {
                  "organizerEmail": "user@user.com",
                  "name": "test entity",
                  "description": "test",
                  "date": "2024-12-12",
                  "time": "05:30:00"
                  }
                """)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();

        int id = JsonPath.read(body, "$.id");

        Optional<EventEntity> createdEventOpt = eventRepository.findById((long) id);

        Assertions.assertTrue(createdEventOpt.isPresent());

        EventEntity created = createdEventOpt.get();

        Assertions.assertEquals("user@user.com", created.getOrganizerEmail());
        Assertions.assertEquals("test entity", created.getName());
        Assertions.assertEquals("test", created.getDescription());
        Assertions.assertEquals("2024-12-12", created.getDate().toString());
        Assertions.assertEquals("05:30:00", created.getTime().toString() + ":00");
    }

    @Test
    public void testDeleteOffer() throws Exception {

        var actualEntity = createTestEvent();

        mockMvc.perform(delete("/events/{id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

        Assertions.assertTrue(
                eventRepository.findById(actualEntity.getId()).isEmpty()
        );
    }

    private EventEntity createTestEvent() {
        return eventRepository.save(
                new EventEntity()
                        .setDescription("test")
                        .setName("test entity")
                        .setDate(LocalDate.of(2024, 12, 12))
                        .setTime(LocalTime.of(5, 30, 0))
                        .setOrganizerEmail("user@user.com")
        );
    }
}
