package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.model.Department;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonMapper {

    private DepartmentMapper departmentMapper;

    private DocumentMapper documentMapper;

    public Person convert(PersonDto dto) {

        Department dep = departmentMapper.convert(dto.getDepartment());
        Document doc = documentMapper.convert(dto.getDocument());
        Person result = new Person();

        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setSurname(dto.getSurname());
        result.setBirthday(dto.getBirthday());
        result.setDepartment(dep);
        result.setDocument(doc);
        return result;
    }

    public PersonDto convert(Person person) {

        PersonDto result = new PersonDto();
        DepartmentDto dep = departmentMapper.convert(person.getDepartment());
        DocumentDto doc = documentMapper.convert(person.getDocument());

        result.setId(person.getId());
        result.setName(person.getName());
        result.setSurname(person.getSurname());
        result.setBirthday(person.getBirthday());
        result.setDepartment(dep);
        result.setDocument(doc);
        return result;
    }
}