package com.example.MovieApp.service;

import com.example.MovieApp.entity.Movie;
import com.example.MovieApp.entity.Review;
import com.example.MovieApp.entity.User;
import com.example.MovieApp.exception.ReviewNotFoundException;
import com.example.MovieApp.repository.MovieRepository;
import com.example.MovieApp.repository.ReviewRepository;
import com.example.MovieApp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository; // Inject MovieRepository
    private final UserRepository userRepository; // Inject UserRepository

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    // Constructor Injection
    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review addReview(Review review) {
        // Check if the movie exists
        Movie movie = movieRepository.findById(review.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Check if the user exists
        User user = userRepository.findById(review.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        review.setMovie(movie);
        review.setUser(user);
        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReview(Long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id " + id));

        validateReview(reviewDetails); // Validate before updating

        review.setRating(reviewDetails.getRating());
        review.setReviewText(reviewDetails.getReviewText());
        review.setReviewDate(LocalDateTime.now()); // Update to current date

        logger.info("Updated review with id: {}", id);

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("Review not found with id " + id); // Check if exists
        }
        reviewRepository.deleteById(id);
        logger.info("Deleted review with id: {}", id);
    }

    private void validateReview(Review review) {
        if (review.getRating() < 1 || review.getRating() > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10.");
        }
    }
}
