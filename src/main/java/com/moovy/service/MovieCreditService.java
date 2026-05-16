package com.moovy.service;

import com.moovy.dto.AddCastRequestDto;
import com.moovy.dto.AddCrewRequestDto;
import com.moovy.dto.CastDto;
import com.moovy.dto.CrewDto;

public interface MovieCreditService {
    CastDto addCastToMovie(Integer movieId, AddCastRequestDto requestDto);
    void removeCastFromMovie(Integer castId);

    CrewDto addCrewToMovie(Integer movieId, AddCrewRequestDto requestDto);
    void removeCrewFromMovie(Integer crewId);
}
