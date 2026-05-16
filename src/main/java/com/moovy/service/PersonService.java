package com.moovy.service;

import com.moovy.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto createPerson(PersonDto personDto);
    PersonDto updatePerson(Integer personId, PersonDto personDto);
    PersonDto getPersonById(Integer personId);
    List<PersonDto> getAllPersons();
    void deletePerson(Integer personId);
}
