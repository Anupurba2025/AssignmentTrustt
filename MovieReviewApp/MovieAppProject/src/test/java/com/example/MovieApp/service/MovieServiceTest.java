package com.example.MovieApp.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.MovieApp.entity.Movie;
import com.example.MovieApp.repository.MovieRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;


    //@MockBean: This will replace the actual MovieRepository bean with a mock one in the context of the test.
    // This ensures that the test does not interact with the real database.
    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void testGetMovieById() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setDirector("Christopher Nolan");
        movie.setReleaseDate(LocalDate.parse("2010-07-16"));

        //In unit testing, mocking is used to simulate behavior without actually accessing a real database.
        Mockito.when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Movie fetchedMovie = movieService.getMovieById(1L);

        assertNotNull(fetchedMovie);
        assertEquals(1L, fetchedMovie.getId());
        assertEquals("Inception", fetchedMovie.getTitle());
    }

    @Test
    public void testGetAllMovie(){
        List<Movie> movies = Arrays.asList(
                new Movie(1L, "Inception", "Sci-Fi", "Christopher Nolan", LocalDate.of(2010, 7, 16)),
                new Movie(2L, "Titanic", "Romance", "James Cameron", LocalDate.of(1997, 12, 19))
        );
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();
        assertNotNull(result);
        assertEquals(2,result.size());
    }

    @Test
    public void testDeletebyID(){
        Movie movie = new Movie(1L,"Inception","Sci-Fi","Christopher Nolan",LocalDate.of(2010, 7, 16));
        Mockito.when(movieRepository.existsById(1L)).thenReturn(true);

        Mockito.doNothing().when(movieRepository).deleteById(1L);

        movieRepository.deleteById(1L);

        Mockito.verify(movieRepository,Mockito.times(1)).deleteById(1L);
    }
}
