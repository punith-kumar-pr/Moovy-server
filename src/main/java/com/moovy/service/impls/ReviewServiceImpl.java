package com.moovy.service.impls;

import com.moovy.dto.ReviewRequestDto;
import com.moovy.dto.ReviewResponseDto;
import com.moovy.entity.Movie;
import com.moovy.entity.Review;
import com.moovy.entity.User;
import com.moovy.exception.BadRequestException;
import com.moovy.exception.ResourceNotFoundException;
import com.moovy.repository.MovieRepository;
import com.moovy.repository.ReviewRepository;
import com.moovy.repository.UserRepository;
import com.moovy.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public ReviewResponseDto addReview(String username, ReviewRequestDto dto) {
        if (dto.getReviewText() == null || dto.getReviewText().isBlank()) {
            throw new BadRequestException("Review text cannot be empty");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + dto.getMovieId()));

        // Check if user already reviewed this movie
        reviewRepository.findByUserAndMovie(user, movie).ifPresent(existing -> {
            throw new BadRequestException("You have already reviewed this movie. Use PUT to update your review.");
        });

        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setReviewText(dto.getReviewText());
        review.setReviewDate(new Date());

        Review saved = reviewRepository.save(review);
        return mapToResponseDto(saved);
    }

    @Override
    public ReviewResponseDto updateReview(String username, ReviewRequestDto dto) {
        if (dto.getReviewText() == null || dto.getReviewText().isBlank()) {
            throw new BadRequestException("Review text cannot be empty");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + dto.getMovieId()));

        Review review = reviewRepository.findByUserAndMovie(user, movie)
                .orElseThrow(() -> new ResourceNotFoundException("You haven't reviewed this movie yet"));

        review.setReviewText(dto.getReviewText());
        review.setReviewDate(new Date());

        Review saved = reviewRepository.save(review);
        return mapToResponseDto(saved);
    }

    @Override
    public List<ReviewResponseDto> getMyReviews(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return reviewRepository.findByUser_UserId(user.getUserId()).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDto> getReviewsForMovie(int movieId) {
        movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + movieId));
        return reviewRepository.findByMovie_MovieId(movieId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteReview(String username, int movieId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + movieId));
        reviewRepository.deleteByUserAndMovie(user, movie);
    }

    private ReviewResponseDto mapToResponseDto(Review review) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setReviewId(review.getReviewId());
        dto.setMovieId(review.getMovie().getMovieId());
        dto.setMovieTitle(review.getMovie().getTitle());
        dto.setUsername(review.getUser().getUsername());
        dto.setReviewText(review.getReviewText());
        dto.setReviewDate(review.getReviewDate());
        return dto;
    }
}
