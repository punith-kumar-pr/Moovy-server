package com.moovy.service.impls;

import com.moovy.dto.MovieResponseDto;
import com.moovy.dto.WatchedMoviesRequestDto;
import com.moovy.entity.Movie;
import com.moovy.entity.User;
import com.moovy.entity.WatchedMovies;
import com.moovy.repository.MovieRepository;
import com.moovy.repository.UserRepository;
import com.moovy.repository.WatchedMoviesRepository;
import com.moovy.service.WatchedMoviesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchedMoviesServiceImpl implements WatchedMoviesService {
    @Autowired
    private WatchedMoviesRepository watchedMoviesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public WatchedMovies addToWatchedMovies(WatchedMoviesRequestDto watchedMoviesRequestDto) {
        User user = userRepository.findById(watchedMoviesRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(watchedMoviesRequestDto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Check if the movie is already in the user's watch list
        if (watchedMoviesRepository.existsByUserAndMovie(user, movie)) {
            throw new RuntimeException("This movie is already in the watch list");
        }

        // Create new WatchedMovies entry
        WatchedMovies watchedMovies = new WatchedMovies();
        watchedMovies.setUser(user);
        watchedMovies.setMovie(movie);

        return watchedMoviesRepository.save(watchedMovies);
    }

    @Override
    public List<MovieResponseDto> getMoviesInWatchedMoviesByUserId(int userId) {
        List<WatchedMovies> watchedMoviess = watchedMoviesRepository.findByUser_UserId(userId);
        return getMoviesInStructure(watchedMoviess);
    }

    @Override
    @Transactional
    public void removeMovieFromWatchedMovies(int userId, int movieId) {
        watchedMoviesRepository.deleteByUser_UserIdAndMovie_MovieId(userId, movieId);
    }

    private List<MovieResponseDto> getMoviesInStructure(List<WatchedMovies> watchedMoviess) {
        return watchedMoviess.stream()
                .map(watchedMovies -> {
                    Movie movie = watchedMovies.getMovie();
                    MovieResponseDto dto = new MovieResponseDto();
                    dto.setId(movie.getMovieId());
                    dto.setTitle(movie.getTitle());
                    dto.setVoteAverage(movie.getVoteAverage());
                    dto.setVoteCount(movie.getVoteCount());
                    dto.setSummary(movie.getSummary());
                    dto.setAdult(movie.getAdult());
                    dto.setImageUrl(movie.getImageUrl());
                    dto.setReleaseDate(movie.getReleaseDate());
                    dto.setRuntime(movie.getRuntime());
                    dto.setTagline(movie.getTagline());
                    dto.setTrailerUrl(movie.getTrailerUrl());

                    // Map genres
                    dto.setGenres(movie.getMovieGenres().stream()
                            .map(mg -> {
                                MovieResponseDto.GenreDto genreDto = new MovieResponseDto.GenreDto();
                                genreDto.setId(mg.getGenre().getGenreId());
                                genreDto.setGenreName(mg.getGenre().getGenreName());
                                return genreDto;
                            })
                            .collect(Collectors.toList()));

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
