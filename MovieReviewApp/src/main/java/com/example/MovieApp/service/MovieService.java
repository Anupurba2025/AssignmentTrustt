package com.example.MovieApp.service;

import com.example.MovieApp.entity.Movie;
import com.example.MovieApp.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setTitle(movieDetails.getTitle());
        movie.setGenre(movieDetails.getGenre());
        movie.setDirector(movieDetails.getDirector());
        movie.setReleaseDate(movieDetails.getReleaseDate());
        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with id " + id); // Handle not found case
        }
        movieRepository.deleteById(id);
    }

    public List<String> getAllMovieNames() {
        return movieRepository.findAll()
                .stream()
                .map(Movie::getTitle)  // Adjust based on your Movie entity's name method
                .collect(Collectors.toList());
    }
}
