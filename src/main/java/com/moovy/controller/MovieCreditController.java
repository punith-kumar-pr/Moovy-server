package com.moovy.controller;

import com.moovy.dto.AddCastRequestDto;
import com.moovy.dto.AddCrewRequestDto;
import com.moovy.dto.CastDto;
import com.moovy.dto.CrewDto;
import com.moovy.service.MovieCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/movies/credits")
@PreAuthorize("hasRole('ADMIN')")
public class MovieCreditController {

    @Autowired
    private MovieCreditService creditService;

    @PostMapping("/{movieId}/cast")
    public ResponseEntity<CastDto> addCast(
            @PathVariable Integer movieId,
            @RequestBody AddCastRequestDto requestDto) {
        CastDto dto = creditService.addCastToMovie(movieId, requestDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/cast/{castId}")
    public ResponseEntity<String> removeCast(@PathVariable Integer castId) {
        creditService.removeCastFromMovie(castId);
        return ResponseEntity.ok("Cast member removed successfully.");
    }

    @PostMapping("/{movieId}/crew")
    public ResponseEntity<CrewDto> addCrew(
            @PathVariable Integer movieId,
            @RequestBody AddCrewRequestDto requestDto) {
        CrewDto dto = creditService.addCrewToMovie(movieId, requestDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/crew/{crewId}")
    public ResponseEntity<String> removeCrew(@PathVariable Integer crewId) {
        creditService.removeCrewFromMovie(crewId);
        return ResponseEntity.ok("Crew member removed successfully.");
    }
}
