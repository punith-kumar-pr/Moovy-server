package com.moovy.service.impls;

import com.moovy.dto.AddCastRequestDto;
import com.moovy.dto.AddCrewRequestDto;
import com.moovy.dto.CastDto;
import com.moovy.dto.CrewDto;
import com.moovy.entity.Movie;
import com.moovy.entity.MovieCast;
import com.moovy.entity.MovieCrew;
import com.moovy.entity.Person;
import com.moovy.exception.ResourceNotFoundException;
import com.moovy.repository.MovieCastRepository;
import com.moovy.repository.MovieCrewRepository;
import com.moovy.repository.MovieRepository;
import com.moovy.repository.PersonRepository;
import com.moovy.service.MovieCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCreditServiceImpl implements MovieCreditService {

    @Autowired
    private MovieCastRepository castRepository;

    @Autowired
    private MovieCrewRepository crewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public CastDto addCastToMovie(Integer movieId, AddCastRequestDto requestDto) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + movieId));
        Person person = personRepository.findById(requestDto.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + requestDto.getPersonId()));

        MovieCast cast = new MovieCast();
        cast.setMovie(movie);
        cast.setPerson(person);
        cast.setCharacterName(requestDto.getCharacterName());
        cast.setOrder(requestDto.getOrder() != null ? requestDto.getOrder() : 0);

        MovieCast saved = castRepository.save(cast);
        return mapToCastDto(saved);
    }

    @Override
    public void removeCastFromMovie(Integer castId) {
        if (!castRepository.existsById(castId)) {
            throw new ResourceNotFoundException("MovieCast mapping not found with ID: " + castId);
        }
        castRepository.deleteById(castId);
    }

    @Override
    public CrewDto addCrewToMovie(Integer movieId, AddCrewRequestDto requestDto) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + movieId));
        Person person = personRepository.findById(requestDto.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + requestDto.getPersonId()));

        MovieCrew crew = new MovieCrew();
        crew.setMovie(movie);
        crew.setPerson(person);
        crew.setJob(requestDto.getJob());
        crew.setDepartment(requestDto.getDepartment());

        MovieCrew saved = crewRepository.save(crew);
        return mapToCrewDto(saved);
    }

    @Override
    public void removeCrewFromMovie(Integer crewId) {
        if (!crewRepository.existsById(crewId)) {
            throw new ResourceNotFoundException("MovieCrew mapping not found with ID: " + crewId);
        }
        crewRepository.deleteById(crewId);
    }

    public static CastDto mapToCastDto(MovieCast cast) {
        if (cast == null) return null;
        CastDto dto = new CastDto();
        dto.setId(cast.getId());
        dto.setPerson(PersonServiceImpl.mapToDto(cast.getPerson()));
        dto.setCharacterName(cast.getCharacterName());
        dto.setOrder(cast.getOrder());
        return dto;
    }

    public static CrewDto mapToCrewDto(MovieCrew crew) {
        if (crew == null) return null;
        CrewDto dto = new CrewDto();
        dto.setId(crew.getId());
        dto.setPerson(PersonServiceImpl.mapToDto(crew.getPerson()));
        dto.setJob(crew.getJob());
        dto.setDepartment(crew.getDepartment());
        return dto;
    }
}
