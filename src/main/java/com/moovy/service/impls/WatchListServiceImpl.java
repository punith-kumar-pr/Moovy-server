// WatchListServiceImpl.java
package com.moovy.service.impls;

import com.moovy.dto.MovieResponseDto;
import com.moovy.dto.WatchListRequestDto;
import com.moovy.entity.Movie;
import com.moovy.entity.User;
import com.moovy.entity.WatchList;
import com.moovy.repository.MovieRepository;
import com.moovy.repository.UserRepository;
import com.moovy.repository.WatchListRepository;
import com.moovy.service.WatchListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListServiceImpl implements WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public WatchList addToWatchList(WatchListRequestDto watchListRequestDto) {
        User user = userRepository.findById(watchListRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(watchListRequestDto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Check if the movie is already in the user's watch list
        if (watchListRepository.existsByUserAndMovie(user, movie)) {
            throw new RuntimeException("This movie is already in the watch list");
        }

        // Create new WatchList entry
        WatchList watchList = new WatchList();
        watchList.setUser(user);
        watchList.setMovie(movie);

        return watchListRepository.save(watchList);
    }

    @Override
    public List<MovieResponseDto> getMoviesInWatchListByUserId(int userId) {
        List<WatchList> watchLists = watchListRepository.findByUser_UserId(userId);
        return getMoviesInStructure(watchLists);
    }

    @Override
    @Transactional
    public void removeMovieFromWatchList(int userId, int movieId) {
        watchListRepository.deleteByUser_UserIdAndMovie_MovieId(userId, movieId);
    }

    private List<MovieResponseDto> getMoviesInStructure(List<WatchList> watchLists) {
        return watchLists.stream()
                .map(watchList -> {
                    Movie movie = watchList.getMovie();
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
