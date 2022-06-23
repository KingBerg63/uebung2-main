package uebung1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MovieControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkGetEmptyListOfMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void checkPostMovieAndGetList() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Who am I\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("Who am I")));
    }

    @Test
    public void checkPostTwoMoviesAndGetList() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"King Kong\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"name\":\"300\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("King Kong")))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("300")));
    }

    @Test
    public void checkPostInvalidMovie() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"namos\":\"Die Unglaublichen\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void checkPostMovieStatusCode() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Top-Gun\"}"))
                .andExpect(status().isCreated());
    }



    @Test
    public void checkGetMovieByInvalidId() throws Exception {
        mockMvc.perform(get("/movies/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void checkGetMovieByValidId() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"GhostBusters\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/movies/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.name", is("GhostBusters")));
    }

    @Test
    public void checkPostInvalidAndValidMovieAndGetList() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"namos\":\"Die Unglaublichen\"}"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"San Andreas\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("San Andreas")));
    }

    @Test
    public void checkGetMovieByInvalidName() throws Exception {
        mockMvc.perform(get("/movies/Scream"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void checkGetMovieByValidName() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Prey\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/movies/Prey"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("Prey")));
    }


    @Test
    public void checkGetTwoMoviesByName() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Prey\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Prey\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/movies/Prey"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("Prey")))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("Prey")));
    }




}
