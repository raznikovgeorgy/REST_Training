package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.model.Department;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.service.DepartmentService;
import com.syncretis.rest_training.service.DocumentService;
import com.syncretis.rest_training.service.LanguageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonMapperTest {
    @Mock
    private DepartmentService departmentService;
    @Mock
    private DocumentService documentService;
    @Mock
    private LanguageService languageService;
    @InjectMocks
    private PersonMapper mapper;

    private final static Long id = 1L;
    private final static String languageName = "RU";
    private final static String docId = "3as7fasd8fsda6fds6a8f53sa7";
    private final static String depName = "Department of Ukrainian cybersecurity";
    private final static LocalDate birthday = LocalDate.of(1997, 3, 12);
    private final static LocalDate docDate = LocalDate.of(2077, 1, 1);
    private Person entity;
    private PersonDto dto;
    private Language language;
    private Document document;
    private Department department;

    @Test
    void shouldConvertDtoToEntity() {
        //GIVEN
        initializeData();
        Person expected = entity;
        LanguageDto languageDto = new LanguageDto(id, languageName);
        DocumentDto documentDto = new DocumentDto(docId, docDate);
        DepartmentDto departmentDto = new DepartmentDto(id, depName);

        when(departmentService.convertToEntity(departmentDto)).thenReturn(department);
        when(documentService.convertToEntity(documentDto)).thenReturn(document);
        when(languageService.convertToEntity(languageDto)).thenReturn(language);
        when(departmentService.get(dto.getDepartmentId())).thenReturn(departmentDto);
        when(documentService.get(dto.getDocumentId())).thenReturn(documentDto);
        when(languageService.get(id)).thenReturn(languageDto);
        //WHEN
        Person actual = mapper.convertToEntity(dto);
        //THEN
        verify(departmentService).convertToEntity(departmentDto);
        verify(documentService).convertToEntity(documentDto);
        verify(languageService).convertToEntity(languageDto);
        verify(departmentService).get(dto.getDepartmentId());
        verify(documentService).get(dto.getDocumentId());
        verify(languageService).get(id);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void convertToDto() {
        //GIVEN
        initializeData();
        PersonDto expected = dto;
        //WHEN
        PersonDto actual = mapper.convertToDto(entity);
        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    void initializeData() {
        department = new Department(id, depName);
        document = new Document(docId, docDate);
        language = new Language(languageName);
        language.setId(id);
        List<Language> languageList = List.of(language);
        List<Long> idList = List.of(id);
        String name = "Georgy";
        String surname = "Raznikov";
        dto = new PersonDto(id, name, surname, birthday, id, docId, idList);
        entity = new Person(name, surname, birthday, department, languageList, document);
        entity.setId(id);
    }
}