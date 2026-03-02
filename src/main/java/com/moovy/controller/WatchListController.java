package com.moovy.controller;

import com.moovy.dto.MovieResponseDto;
import com.moovy.dto.WatchListRequestDto;
import com.moovy.dto.WatchListResponseDto;
import com.moovy.entity.WatchList;
import com.moovy.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watchlist")
public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @PostMapping
    public ResponseEntity<WatchListResponseDto> addToWatchList(@RequestBody WatchListRequestDto watchListRequestDto) {
        WatchList watchList = watchListService.addToWatchList(watchListRequestDto);

        WatchListResponseDto responseDto = new WatchListResponseDto();
        responseDto.setId(watchList.getId());
        responseDto.setUserId(watchList.getUser().getUserId());
        responseDto.setMovieId(watchList.getMovie().getMovieId());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{userId}/movies")
    public ResponseEntity<List<MovieResponseDto>> getMoviesInWatchList(@PathVariable int userId) {
        List<MovieResponseDto> movies = watchListService.getMoviesInWatchListByUserId(userId);
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("user/{userId}/remove-movie/{movieId}")
    public ResponseEntity<String> removeMovieFromWatchList(
            @PathVariable int userId,
            @PathVariable int movieId) {

        watchListService.removeMovieFromWatchList(userId, movieId);
        return ResponseEntity.ok("Movie removed from watch list successfully.");
    }
}
