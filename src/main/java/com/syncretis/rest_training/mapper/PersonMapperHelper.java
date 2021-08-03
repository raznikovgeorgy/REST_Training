package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.repository.DepartmentRepository;
import com.syncretis.rest_training.repository.DocumentRepository;
import com.syncretis.rest_training.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PersonMapperHelper {

    private DepartmentRepository departmentRepository;
    private DocumentRepository documentRepository;
    private LanguageRepository languageRepository;

    public void setPersonObjectData(Person person, PersonDto dto) {
        person.setDepartment(departmentRepository.getById(dto.getDepartment_id()));
        person.setDocument(documentRepository.getById(dto.getDocument_id()));

        List<Long> languageIds = dto.getLanguageIds();
        List<Language> languageList = new ArrayList<>();
        for (Long id : languageIds) {
            languageList.add(languageRepository.getById(id));
        }
        person.setLanguages(languageList);
    }

    public void setLanguagesToDto(Person person, PersonDto dto) {
        List<Language> languageList = person.getLanguages();
        List<Long> languageIds = new ArrayList<>();
        for (Language language : languageList) {
            languageIds.add(language.getId());
        }
        dto.setLanguageIds(languageIds);
    }
}