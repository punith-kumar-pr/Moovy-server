package com.moovy.service.impls;

import com.moovy.dto.PersonDto;
import com.moovy.entity.Person;
import com.moovy.exception.ResourceNotFoundException;
import com.moovy.repository.PersonRepository;
import com.moovy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonDto createPerson(PersonDto dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setBirthdate(dto.getBirthdate());
        person.setNationality(dto.getNationality());
        person.setProfileImageUrl(dto.getProfileImageUrl());
        person.setBiography(dto.getBiography());

        Person saved = personRepository.save(person);
        return mapToDto(saved);
    }

    @Override
    public PersonDto updatePerson(Integer personId, PersonDto dto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));

        if (dto.getName() != null) person.setName(dto.getName());
        if (dto.getBirthdate() != null) person.setBirthdate(dto.getBirthdate());
        if (dto.getNationality() != null) person.setNationality(dto.getNationality());
        if (dto.getProfileImageUrl() != null) person.setProfileImageUrl(dto.getProfileImageUrl());
        if (dto.getBiography() != null) person.setBiography(dto.getBiography());

        Person updated = personRepository.save(person);
        return mapToDto(updated);
    }

    @Override
    public PersonDto getPersonById(Integer personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));
        return mapToDto(person);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream()
                .map(PersonServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePerson(Integer personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));
        personRepository.delete(person);
    }

    // Helper method to map Entity to DTO. Can be made public static if needed elsewhere.
    public static PersonDto mapToDto(Person person) {
        if (person == null) return null;
        PersonDto dto = new PersonDto();
        dto.setPersonId(person.getPersonId());
        dto.setName(person.getName());
        dto.setBirthdate(person.getBirthdate());
        dto.setNationality(person.getNationality());
        dto.setProfileImageUrl(person.getProfileImageUrl());
        dto.setBiography(person.getBiography());
        return dto;
    }
}
