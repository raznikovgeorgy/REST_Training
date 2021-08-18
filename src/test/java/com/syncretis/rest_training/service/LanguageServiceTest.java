package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.mapper.LanguageMapper;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private LanguageMapper languageMapper;
    @InjectMocks
    private LanguageService service;
    private final static Long id = 1L;
    private final static String name = "RU";
    private Language entity;
    private LanguageDto dto;

    @Test
    void shouldDeleteEntityFromDb() {
        //GIVEN
        when(languageRepository.existsById(id)).thenReturn(true);
        //WHEN
        service.delete(id);
        //THEN
        verify(languageRepository).deleteById(id);
    }

    @Test
    void shouldGiveAllEntitiesContainingInDb() {
        //GIVEN
        initializeData();
        List<Language> entityList = new ArrayList<>();
        List<LanguageDto> expected = new ArrayList<>();
        entityList.add(entity);
        entityList.add(entity);
        entityList.add(entity);
        expected.add(dto);
        expected.add(dto);
        expected.add(dto);
        when(languageRepository.findAllByOrderByIdAsc()).thenReturn(entityList);
        when(languageMapper.convertToDto(entity)).thenReturn(dto);
        //WHEN
        List<LanguageDto> actual = service.findAll();
        //THEN
        verify(languageRepository).findAllByOrderByIdAsc();
        verify(languageMapper, times(3)).convertToDto(entity);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGiveEntityFromDbById() {
        //GIVEN
        initializeData();
        when(languageMapper.convertToDto(entity)).thenReturn(dto);
        when(languageRepository.findById(id)).thenReturn(Optional.of(entity));
        LanguageDto expected = new LanguageDto(id, "RU");
        //WHEN
        LanguageDto actual = service.get(id);
        //THEN
        verify(languageRepository).findById(id);
        verify(languageMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldSaveEntityInDb() {
        //GIVEN
        initializeData();
        LanguageDto expected = new LanguageDto(id, "RU");
        when(languageRepository.save(entity)).thenReturn(entity);
        when(languageMapper.convertToDto(entity)).thenReturn(dto);
        when(languageMapper.convertToEntity(dto)).thenReturn(entity);
        //WHEN
        LanguageDto actual = service.save(dto);
        //THEN
        verify(languageRepository).save(entity);
        verify(languageMapper).convertToDto(entity);
        verify(languageMapper).convertToEntity(dto);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldUpdateEntityDataInDb() {
        //GIVEN
        initializeData();
        LanguageDto updatedDto = new LanguageDto();
        updatedDto.setName("RU");
        Language updatedEntity = new Language("RU");
        updatedEntity.setId(id);
        when(languageMapper.convertToEntity(updatedDto)).thenReturn(updatedEntity);
        when(languageMapper.convertToDto(entity)).thenReturn(updatedDto);
        when(languageRepository.save(updatedEntity)).thenReturn(entity);
        //WHEN
        LanguageDto actual = service.update(id, updatedDto);
        //THEN
        verify(languageRepository).save(updatedEntity);
        verify(languageMapper).convertToDto(entity);
        verify(languageMapper).convertToEntity(updatedDto);
        assertThat(actual).isEqualTo(updatedDto);
    }

    @Test
    void shouldConvertEntityToDto() {
        //GIVEN
        initializeData();
        when(languageMapper.convertToDto(entity)).thenReturn(dto);
        //WHEN
        LanguageDto actual = service.convertToDto(entity);
        //THEN
        verify(languageMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    void shouldConvertDtoToEntity() {
        //GIVEN
        initializeData();
        when(languageMapper.convertToEntity(dto)).thenReturn(entity);
        //WHEN
        Language actual = service.convertToEntity(dto);
        //THEN
        verify(languageMapper).convertToEntity(dto);
        assertThat(actual).isEqualTo(entity);
    }

    @Test
    void shouldCheckForExistsEntityInDb() {
        //GIVEN
        when(languageRepository.existsById(id)).thenReturn(true);
        //WHEN
        boolean actual = service.isExist(id);
        //THEN
        verify(languageRepository).existsById(id);
        assertThat(actual).isEqualTo(true);
    }

    private void initializeData() {
        entity = new Language(name);
        dto = new LanguageDto(id, name);
    }
}