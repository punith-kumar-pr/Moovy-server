package com.moovy.service.impls;

import com.moovy.dto.RatingRequestDto;
import com.moovy.dto.RatingResponseDto;
import com.moovy.entity.Movie;
import com.moovy.entity.Rating;
import com.moovy.entity.User;
import com.moovy.exception.BadRequestException;
import com.moovy.exception.DuplicateResourceException;
import com.moovy.exception.ResourceNotFoundException;
import com.moovy.repository.MovieRepository;
import com.moovy.repository.RatingRepository;
import com.moovy.repository.UserRepository;
import com.moovy.service.RatingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public RatingResponseDto rateMovie(String username, RatingRequestDto dto) {
        validateRatingValue(dto.getRating());

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + dto.getMovieId()));

        if (ratingRepository.existsByUserAndMovie(user, movie)) {
            throw new DuplicateResourceException("You have already rated this movie. Use PUT to update your rating.");
        }

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setMovie(movie);
        rating.setRating(dto.getRating());
        rating.setRatingDate(LocalDate.now());

        Rating saved = ratingRepository.save(rating);
        return mapToResponseDto(saved);
    }

    @Override
    public RatingResponseDto updateRating(String username, RatingRequestDto dto) {
        validateRatingValue(dto.getRating());

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + dto.getMovieId()));

        Rating rating = ratingRepository.findByUserAndMovie(user, movie)
                .orElseThrow(() -> new ResourceNotFoundException("You haven't rated this movie yet"));

        rating.setRating(dto.getRating());
        rating.setRatingDate(LocalDate.now());

        Rating saved = ratingRepository.save(rating);
        return mapToResponseDto(saved);
    }

    @Override
    public List<RatingResponseDto> getMyRatings(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return ratingRepository.findByUser_UserId(user.getUserId()).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingResponseDto> getRatingsForMovie(int movieId) {
        movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + movieId));
        return ratingRepository.findByMovie_MovieId(movieId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRating(String username, int movieId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + movieId));
        ratingRepository.deleteByUserAndMovie(user, movie);
    }

    private void validateRatingValue(int rating) {
        if (rating < 1 || rating > 10) {
            throw new BadRequestException("Rating must be between 1 and 10");
        }
    }

    private RatingResponseDto mapToResponseDto(Rating rating) {
        RatingResponseDto dto = new RatingResponseDto();
        dto.setRatingId(rating.getRatingId());
        dto.setMovieId(rating.getMovie().getMovieId());
        dto.setMovieTitle(rating.getMovie().getTitle());
        dto.setRating(rating.getRating());
        dto.setRatingDate(rating.getRatingDate());
        return dto;
    }
}
