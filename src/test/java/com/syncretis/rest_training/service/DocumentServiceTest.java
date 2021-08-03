package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.mapper.DocumentMapper;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {
    @Mock
    private DocumentRepository documentRepository;
    @Mock
    private DocumentMapper documentMapper;
    @InjectMocks
    private DocumentService service;
    private final String id = "y8h647b8f64b7fh874nfg3nh4f";
    private final LocalDate date = LocalDate.of(2077, 1, 1);
    private final Document entity = new Document(id, date);
    private final DocumentDto dto = new DocumentDto(id, date);

    @Test
    void shouldDeleteEntityFromDb() {
        //WHEN
        doNothing().when(documentRepository).deleteById(id);
        when(documentRepository.existsById(id)).thenReturn(true);
        service.delete(id);
        //THEN
        verify(documentRepository).deleteById(id);
    }

    @Test
    void shouldGiveAllEntitiesContainingInDb() {
        //GIVEN
        List<Document> entityList = new ArrayList<>();
        List<DocumentDto> expected = new ArrayList<>();
        entityList.add(entity);
        entityList.add(entity);
        entityList.add(entity);
        expected.add(dto);
        expected.add(dto);
        expected.add(dto);
        //WHEN
        when(documentRepository.findAllByOrderByIdAsc()).thenReturn(entityList);
        when(documentMapper.convertToDto(entity)).thenReturn(dto);
        List<DocumentDto> actual = service.findAll();
        //THEN
        verify(documentMapper, times(3)).convertToDto(entity);
        verify(documentRepository).findAllByOrderByIdAsc();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGiveEntityFromDbById() {
        //GIVEN
        DocumentDto expected = new DocumentDto(id, date);
        when(documentMapper.convertToDto(entity)).thenReturn(dto);
        when(documentRepository.findById(id)).thenReturn(Optional.of(entity));
        //WHEN
        final DocumentDto actual = service.get(id);
        //THEN
        verify(documentRepository).findById(id);
        verify(documentMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldSaveEntityInDb() {
        //GIVEN
        DocumentDto expected = new DocumentDto(id, date);
        when(documentRepository.save(entity)).thenReturn(entity);
        when(documentMapper.convertToDto(entity)).thenReturn(dto);
        when(documentMapper.convertToEntity(dto)).thenReturn(entity);
        //WHEN
        DocumentDto actual = service.save(dto);
        //THEN
        verify(documentRepository).save(entity);
        verify(documentMapper).convertToDto(entity);
        verify(documentMapper).convertToEntity(dto);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    void shouldUpdateEntityDataInDb() {
        //GIVEN
        DocumentDto updatedDto = new DocumentDto();
        updatedDto.setExpireDate(date);
        Document updatedEntity = new Document(id, date);
        when(documentMapper.convertToDto(entity)).thenReturn(updatedDto);
        when(documentMapper.convertToEntity(updatedDto)).thenReturn(updatedEntity);
        when(documentRepository.save(updatedEntity)).thenReturn(entity);
        //WHEN
        DocumentDto actual = service.update(id, updatedDto);
        //THEN
        verify(documentRepository).save(updatedEntity);
        verify(documentMapper).convertToDto(entity);
        verify(documentMapper).convertToEntity(updatedDto);
        assertThat(actual).isEqualTo(updatedDto);
    }

    @Test
    void shouldConvertEntityToDto() {
        //GIVEN
        when(documentMapper.convertToDto(entity)).thenReturn(dto);
        //WHEN
        DocumentDto actual = service.convertToDto(entity);
        //THEN
        verify(documentMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    void shouldConvertDtoToEntity() {
        //GIVEN
        when(documentMapper.convertToEntity(dto)).thenReturn(entity);
        //WHEN
        Document actual = service.convertToEntity(dto);
        //THEN
        verify(documentMapper).convertToEntity(dto);
        assertThat(actual).isEqualTo(entity);
    }

    @Test
    void shouldCheckForExistsEntityInDb() {
        //GIVEN
        when(documentRepository.existsById(id)).thenReturn(true);
        //WHEN
        boolean actual = service.isExist(id);
        //THEN
        verify(documentRepository).existsById(id);
        assertThat(actual).isEqualTo(true);
    }
}