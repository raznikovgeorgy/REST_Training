package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.mapper.PersonMapper;
import com.syncretis.rest_training.model.Department;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.repository.PersonRepository;
import com.syncretis.rest_training.validation.personDtoValidation.PersonValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;
    @Mock
    private PersonValidator personValidator;
    @InjectMocks
    private PersonService service;
    private final Long id = 1L;
    private final Long anotherId = 2L;
    private final Language language = new Language("RU");
    private final List<Language> languageList = List.of(language);
    private final String name = "Georgy";
    private final String surname = "Raznikov";
    private final String anotherName = "Amir";
    private final String anotherSurname = "Kadyrov";
    private final String docId = "3as7fasd8fsda6fds6a8f53sa7";
    private final String anotherDocId = "jty8u6s7l343k8672g876jk";
    private final String depName = "Department of Ukrainian cybersecurity";
    private final LocalDate birthday = LocalDate.of(1997, 3, 12);
    private final LocalDate docDate = LocalDate.of(2077, 1, 1);
    private final Department department = new Department(id, depName);
    private final Document document = new Document(docId, docDate);
    private final Document anotherDocument = new Document(anotherDocId, LocalDate.of(2133, 1, 1));
    private final Person entity = new Person(name, surname, birthday, department, languageList, document);
    private final Person anotherEntity = new Person(anotherName, anotherSurname,
            LocalDate.of(1997, 4, 16), department, List.of(language), anotherDocument);
    private final List<Long> idList = List.of(id);
    private final PersonDto dto = new PersonDto(id, name, surname, birthday, id, docId, idList);
    private final PersonDto anotherDto = new PersonDto(anotherId, anotherName, anotherSurname, LocalDate.of(1997, 4, 16), id, anotherDocId, List.of(id));
    private final List<PersonDto> dtoList = List.of(dto, anotherDto);
    private final List<Person> entityList = List.of(entity, anotherEntity);

    @BeforeEach
    void setUp() {
        language.setId(id);
        entity.setId(id);
        anotherEntity.setId(anotherId);
    }

    @Test
    void shouldDeleteEntityFromDb() {
        //GIVEN
        when(personRepository.existsById(id)).thenReturn(true);
        //WHEN
        service.delete(id);
        //THEN
        verify(personRepository).deleteById(id);
    }

    @Test
    void shouldDeleteAllEntitiesContainingInDb() {
        //GIVEN
        when(service.convertToEntity(dto)).thenReturn(entity);
        when(service.convertToEntity(anotherDto)).thenReturn(anotherEntity);
        when(personRepository.existsById(any(Long.class))).thenReturn(true);
        //WHEN
        service.deleteAll(dtoList);
        //THEN
        verify(personRepository).deleteAllInBatch(entityList);
        verify(personMapper, times(2)).convertToEntity(any(PersonDto.class));
        verify(personRepository, times(2)).existsById(any(Long.class));
    }

    @Test
    void shouldReturnAllEntitiesFromDb() {
        //GIVEN
        when(personRepository.findAllByOrderByIdAsc()).thenReturn(entityList);
        when(personMapper.convertToDto(entity)).thenReturn(dto);
        when(personMapper.convertToDto(anotherEntity)).thenReturn(anotherDto);
        //WHEN
        List<PersonDto> actual = service.findAll();
        //THEN
        verify(personRepository).findAllByOrderByIdAsc();
        verify(personMapper, times(2)).convertToDto(any(Person.class));
        assertThat(actual).isEqualTo(dtoList);
    }

    @Test
    void shouldReturnAnEntityById() {
        //GIVEN
        when(personRepository.findById(id)).thenReturn(Optional.of(entity));
        when(personMapper.convertToDto(entity)).thenReturn(dto);
        //WHEN
        PersonDto actual = service.get(id);
        //THEN
        verify(personRepository).findById(id);
        verify(personMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    void shouldSaveAnEntityToDb() {
        //GIVEN
        when(personRepository.save(entity)).thenReturn(entity);
        when(personMapper.convertToDto(entity)).thenReturn(dto);
        when(personMapper.convertToEntity(dto)).thenReturn(entity);
        //WHEN
        PersonDto actual = service.save(dto);
        //THEN
        verify(personRepository).save(entity);
        verify(personMapper).convertToDto(entity);
        verify(personMapper).convertToEntity(dto);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    void shouldSaveAllGivenEntities() {
        //GIVEN
        when(personMapper.convertToEntity(dto)).thenReturn(entity);
        when(personMapper.convertToEntity(anotherDto)).thenReturn(anotherEntity);
        when(personRepository.saveAll(entityList)).thenReturn(entityList);
        when(personMapper.convertToDto(entity)).thenReturn(dto);
        when(personMapper.convertToDto(anotherEntity)).thenReturn(anotherDto);
        //WHEN
        List<PersonDto> actual = service.saveAll(dtoList);
        //THEN
        verify(personRepository).saveAll(any());
        verify(personMapper, times(2)).convertToEntity(any(PersonDto.class));
        verify(personMapper, times(2)).convertToDto(any(Person.class));
        assertThat(actual).isEqualTo(dtoList);
    }

    @Test
    void shouldUpdateAnEntityInDb() {
        //GIVEN
        when(personValidator.validate(anotherDto)).thenReturn(true);
        when(personMapper.convertToEntity(anotherDto)).thenReturn(anotherEntity);
        when(personMapper.convertToDto(anotherEntity)).thenReturn(anotherDto);
        when(personRepository.save(anotherEntity)).thenReturn(anotherEntity);
        //WHEN
        PersonDto actual = service.update(anotherId, anotherDto);
        //THEN
        verify(personMapper).convertToDto(anotherEntity);
        verify(personMapper).convertToEntity(anotherDto);
        verify(personRepository).save(anotherEntity);
        assertThat(actual).isEqualTo(anotherDto);
    }

    @Test
    void shouldConvertAnEntityToDto() {

        //GIVEN
        when(personMapper.convertToDto(entity)).thenReturn(dto);
        //WHEN
        PersonDto actual = service.convertToDto(entity);
        //THEN
        verify(personMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    void shouldConvertDtoToEntity() {
        //GIVEN
        when(personMapper.convertToEntity(dto)).thenReturn(entity);
        //WHEN
        Person actual = service.convertToEntity(dto);
        //THEN
        verify(personMapper).convertToEntity(dto);
        assertThat(actual).isEqualTo(entity);
    }
}