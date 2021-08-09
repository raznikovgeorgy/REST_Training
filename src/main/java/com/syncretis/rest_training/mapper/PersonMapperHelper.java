package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.exception.DepartmentNotFoundException;
import com.syncretis.rest_training.exception.DocumentNotFoundException;
import com.syncretis.rest_training.exception.LanguageNotFoundException;
import com.syncretis.rest_training.model.Department;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.repository.DepartmentRepository;
import com.syncretis.rest_training.repository.DocumentRepository;
import com.syncretis.rest_training.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PersonMapperHelper {

    private DepartmentRepository departmentRepository;
    private DocumentRepository documentRepository;
    private LanguageRepository languageRepository;

    public void setPersonObjectData(Person person, PersonDto dto) {
        Department department = departmentRepository.findById
                (dto.getDepartment_id()).orElseThrow(() -> new DepartmentNotFoundException(dto.getDepartment_id()));
        person.setDepartment(department);

        Document document = documentRepository.findById
                (dto.getDocument_id()).orElseThrow(() -> new DocumentNotFoundException(dto.getDocument_id()));
        person.setDocument(document);

        List<Long> languageIds = dto.getLanguageIds();
        List<Language> languageList = new ArrayList<>();
        for (Long id : languageIds) {
            Language language = languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
            languageList.add(language);
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