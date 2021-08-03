package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonMapper {

    private final PersonMapperHelper helper;

    public Person convert(PersonDto dto) {
        Person result = new Person();

        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setSurname(dto.getSurname());
        result.setBirthday(dto.getBirthday());
        helper.setPersonObjectData(result, dto);
        return result;
    }

    public PersonDto convert(Person person) {
        PersonDto result = new PersonDto();

        result.setId(person.getId());
        result.setName(person.getName());
        result.setSurname(person.getSurname());
        result.setBirthday(person.getBirthday());
        result.setDepartment_id(person.getDepartment().getId());
        result.setDocument_id(person.getDocument().getId());
        helper.setLanguagesToDto(person, result);
        return result;
    }
}