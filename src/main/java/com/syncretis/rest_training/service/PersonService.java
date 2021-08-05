package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.exception.DepartmentNotFoundException;
import com.syncretis.rest_training.exception.personException.PersonNotFoundException;
import com.syncretis.rest_training.exception.personException.YearBirthdayInvalidException;
import com.syncretis.rest_training.mapper.PersonMapper;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.repository.PersonRepository;
import com.syncretis.rest_training.validation.personDtoValidation.YearBirthdayValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
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
    @Autowired
    private YearBirthdayValidator yearBirthdayValidator;

    public void delete(@Min(1) Long id) {
        if (id == null || isExist(id)) {
            throw new PersonNotFoundException(id);
        } else {
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

    public PersonDto get(@Min(1) Long id) {
        return convertToDto(personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id)));
    }

    public PersonDto save(@Valid PersonDto dto) {
        if(dto.getBirthday().getYear() < 1900) {
            throw new YearBirthdayInvalidException();
        } else
        return convertToDto(personRepository.save(convertToEntity(dto)));
    }

    public List<PersonDto> saveAll(List<PersonDto> list) {
        for (PersonDto personDto : list) {
            if(!isExist(personDto.getId())) {
                throw new PersonNotFoundException(personDto.getId());
            }
        }
        List<Person> persons = personRepository.saveAll(list.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        return persons.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PersonDto update(@Min(1) Long id, @Valid PersonDto dto) {
        dto.setId(id);
        DataBinder dataBinder = new DataBinder(dto);
        dataBinder.addValidators(yearBirthdayValidator);
        dataBinder.validate();
        if(dataBinder.getBindingResult().hasErrors()) {
            throw new YearBirthdayInvalidException();
        }
        return convertToDto(personRepository.save(convertToEntity(dto)));
    }

    public PersonDto convertToDto(Person p) {
        return personMapper.convert(p);
    }

    public Person convertToEntity(PersonDto dto) {
        return personMapper.convert(dto);
    }

    public boolean isExist(Long id) {
        return personRepository.existsById(id);
    }

}
