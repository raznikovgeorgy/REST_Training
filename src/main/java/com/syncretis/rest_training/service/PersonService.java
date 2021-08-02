package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.exception.PersonNotFoundException;
import com.syncretis.rest_training.mapper.PersonMapper;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository personRepository;

    private PersonMapper personMapper;

    public void delete(Long id) {
        if (id != null) {
            personRepository.deleteById(id);
        }
    }

    public void deleteAll(List<PersonDto> list) {
        personRepository.deleteAllInBatch(list.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
    }

    public List<PersonDto> findAll() {
        return personRepository.findAllByOrderByIdAsc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PersonDto get(Long id) {
        return convertToDto(personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id)));
    }

    public PersonDto save(PersonDto dto) {
        return convertToDto(personRepository.save(convertToEntity(dto)));
    }

    public List<PersonDto> saveAll(List<PersonDto> list) {
        List<Person> persons = personRepository.saveAll(list.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        return persons.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PersonDto update(Long id, PersonDto dto) {
        dto.setId(id);
        return convertToDto(personRepository.save(convertToEntity(dto)));
    }

    public PersonDto convertToDto(Person p) {
        return personMapper.convert(p);
    }

    public Person convertToEntity(PersonDto dto) {
        return personMapper.convert(dto);
    }
}