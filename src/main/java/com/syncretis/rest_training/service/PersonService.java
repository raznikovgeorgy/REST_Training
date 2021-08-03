package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.exception.person.PersonNotFoundException;
import com.syncretis.rest_training.exception.person.YearOfBirthdayInvalidException;
import com.syncretis.rest_training.mapper.PersonMapper;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.repository.PersonRepository;
import com.syncretis.rest_training.validation.personDtoValidation.PersonValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper;
    private PersonValidator validator;

    public void delete(@Min(1) Long id) {
        if (id == null || !isExist(id)) {
            throw new PersonNotFoundException(id);
        } else {
            personRepository.deleteById(id);
        }
    }

    public void deleteAll(List<PersonDto> list) {
        for (PersonDto dto : list) {
            if (dto.getId() != null && !isExist(dto.getId()))
                throw new PersonNotFoundException(dto.getId(), "\nOperation was aborted");
        }
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

    public PersonDto get(@Min(1) Long id) {
        return convertToDto(personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id)));
    }

    public PersonDto save(@Valid PersonDto dto) {
        if (dto.getBirthday().getYear() < 1900) {
            throw new YearOfBirthdayInvalidException();
        } else
            return convertToDto(personRepository.save(convertToEntity(dto)));
    }

    public List<PersonDto> saveAll(List<PersonDto> list) {
        return personRepository.saveAll(list.stream()
                        .map(this::convertToEntity)
                        .collect(Collectors.toList())).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PersonDto update(@Min(1) Long id, @Valid PersonDto dto) {
        dto.setId(id);
        if (!validator.validate(dto))
            throw new YearOfBirthdayInvalidException();
        return convertToDto(personRepository.save(convertToEntity(dto)));
    }

    public PersonDto convertToDto(Person p) {
        return personMapper.convertToDto(p);
    }

    public Person convertToEntity(PersonDto dto) {
        return personMapper.convertToEntity(dto);
    }

    private boolean isExist(Long id) {
        return personRepository.existsById(id);
    }
}