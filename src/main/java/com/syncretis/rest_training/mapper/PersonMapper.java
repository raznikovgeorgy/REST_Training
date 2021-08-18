package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.service.DepartmentService;
import com.syncretis.rest_training.service.DocumentService;
import com.syncretis.rest_training.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PersonMapper {
    private DepartmentService departmentService;
    private DocumentService documentService;
    private LanguageService languageService;

    public Person convertToEntity(PersonDto dto) {
        Person result = new Person();

        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setSurname(dto.getSurname());
        result.setBirthday(dto.getBirthday());
        result.setDepartment(departmentService.convertToEntity(departmentService.get(dto.getDepartmentId())));
        result.setDocument(documentService.convertToEntity(documentService.get(dto.getDocumentId())));
        List<Long> languageIds = dto.getLanguageIds();
        List<Language> languageList = new ArrayList<>();
        for (Long id : languageIds) {
            Language language = languageService.convertToEntity(languageService.get(id));
            languageList.add(language);
        }
        result.setLanguages(languageList);
        return result;
    }

    public PersonDto convertToDto(Person person) {
        PersonDto result = new PersonDto();
        result.setId(person.getId());
        result.setName(person.getName());
        result.setSurname(person.getSurname());
        result.setBirthday(person.getBirthday());
        result.setDepartmentId(person.getDepartment().getId());
        result.setDocumentId(person.getDocument().getId());
        List<Language> languageList = person.getLanguages();
        List<Long> languageIds = new ArrayList<>();
        for (Language language : languageList) {
            languageIds.add(language.getId());
        }
        result.setLanguageIds(languageIds);
        return result;
    }
}