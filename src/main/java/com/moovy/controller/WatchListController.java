package com.moovy.controller;

import com.moovy.dto.MovieResponseDto;
import com.moovy.dto.WatchListRequestDto;
import com.moovy.dto.WatchListResponseDto;
import com.moovy.entity.WatchList;
import com.moovy.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/watchlist")
public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @PostMapping
    public ResponseEntity<WatchListResponseDto> addToWatchList(
            @RequestBody WatchListRequestDto watchListRequestDto,
            Principal principal) {
        WatchList watchList = watchListService.addToWatchList(
                principal.getName(), watchListRequestDto.getMovieId());

        WatchListResponseDto responseDto = new WatchListResponseDto();
        responseDto.setId(watchList.getId());
        responseDto.setUserId(watchList.getUser().getUserId());
        responseDto.setMovieId(watchList.getMovie().getMovieId());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getMoviesInWatchList(Principal principal) {
        List<MovieResponseDto> movies = watchListService.getMoviesInWatchList(principal.getName());
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> removeMovieFromWatchList(
            @PathVariable int movieId,
            Principal principal) {
        watchListService.removeMovieFromWatchList(principal.getName(), movieId);
        return ResponseEntity.ok("Movie removed from watch list successfully.");
    }
}
