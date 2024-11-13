package com.example.MovieApp.controller;

import com.example.MovieApp.entity.Movie;
import com.example.MovieApp.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    //MockMvc is a class in Spring Boot's testing framework, specifically used for testing Spring MVC controllers. It allows you to simulate
    // HTTP requests and verify responses without starting a real web server.
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;

    @Test
    public void testGetMovieById() throws Exception {
        // This test simulates a GET request to /api/1 and expects a 200 OK response
        // Mock the Movie object
        Movie movie = new Movie(1L, "Inception", "Sci-Fi", "Christopher Nolan", LocalDate.of(2010, 7, 16));
        Mockito.when(movieService.getMovieById(1L)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))  // Expects the movie ID to be 1
                .andExpect(jsonPath("$.title").value("Inception"))  // Expects the movie title to be Inception
                .andExpect(jsonPath("$.director").value("Christopher Nolan"));  // Expects the director's name
    }

    @Test
    public void testGetAllMovie() throws Exception{
        // Mock the MovieService to return exactly 2 movie names
        Mockito.when(movieService.getAllMovieNames()).thenReturn(List.of("Movie 1", "Movie 2"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/names"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("Movie 1"))
                .andExpect(jsonPath("[1]").value("Movie 2"));
    }

    @Test
    public void testDeleteMovieByID() throws Exception{
        Mockito.doNothing().when(movieService).deleteMovie(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(movieService,Mockito.times(1)).deleteMovie(1L);
    }

}
