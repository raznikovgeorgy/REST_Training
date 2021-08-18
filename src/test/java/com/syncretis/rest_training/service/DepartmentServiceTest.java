package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.mapper.DepartmentMapper;
import com.syncretis.rest_training.model.Department;
import com.syncretis.rest_training.repository.DepartmentRepository;
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
class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private DepartmentMapper departmentMapper;
    @InjectMocks
    private DepartmentService service;

    private Department entity;
    private DepartmentDto dto;
    private final static Long id = 1L;
    private final static String depName = "Department of Good Music";

    @Test
    void shouldDeleteEntityFromDb() {
        //GIVEN
        when(departmentRepository.existsById(id)).thenReturn(true);
        //WHEN
        service.delete(id);
        //THEN
        verify(departmentRepository).deleteById(id);
        verify(departmentRepository).existsById(id);
    }

    @Test
    void shouldGiveAllEntitiesContainingInDb() {
        //GIVEN
        initializeData();
        List<Department> entityList = new ArrayList<>();
        List<DepartmentDto> expected = new ArrayList<>();
        entityList.add(entity);
        entityList.add(entity);
        entityList.add(entity);
        expected.add(dto);
        expected.add(dto);
        expected.add(dto);

        when(departmentMapper.convertToDto(entity)).thenReturn(dto);
        when(departmentRepository.findAllByOrderByIdAsc()).thenReturn(entityList);
        //WHEN
        List<DepartmentDto> actual = service.findAll();
        //THEN
        verify(departmentMapper, times(3)).convertToDto(entity);
        verify(departmentRepository).findAllByOrderByIdAsc();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGiveEntityFromDbById() {
        //GIVEN
        initializeData();
        DepartmentDto expected = new DepartmentDto(id, depName);
        when(departmentMapper.convertToDto(entity)).thenReturn(dto);
        when(departmentRepository.findById(id)).thenReturn(Optional.of(entity));
        //WHEN
        DepartmentDto actual = service.get(id);
        //THEN
        verify(departmentRepository).findById(id);
        verify(departmentMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldSaveEntityInDb() {
        //GIVEN
        initializeData();
        DepartmentDto expected = new DepartmentDto(id, depName);
        when(departmentRepository.save(entity)).thenReturn(entity);
        when(departmentMapper.convertToDto(entity)).thenReturn(dto);
        when(departmentMapper.convertToEntity(dto)).thenReturn(entity);
        //WHEN
        DepartmentDto actual = service.save(dto);
        //THEN
        verify(departmentMapper).convertToEntity(dto);
        verify(departmentMapper).convertToDto(entity);
        verify(departmentRepository).save(entity);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldUpdateEntityDataInDb() {
        //GIVEN
        initializeData();
        DepartmentDto updatedDto = new DepartmentDto();
        String newDepName = "Department of Good Music";
        updatedDto.setName(newDepName);
        Department updatedEntity = new Department(id, newDepName);
        //WHEN
        when(departmentMapper.convertToEntity(updatedDto)).thenReturn(updatedEntity);
        when(departmentMapper.convertToDto(entity)).thenReturn(updatedDto);
        when(departmentRepository.save(updatedEntity)).thenReturn(entity);
        DepartmentDto actual = service.update(id, updatedDto);
        //THEN
        verify(departmentRepository).save(updatedEntity);
        verify(departmentMapper).convertToDto(entity);
        verify(departmentMapper).convertToEntity(updatedDto);
        assertThat(actual).isEqualTo(updatedDto);
    }

    @Test
    void shouldConvertEntityToDto() {
        //GIVEN
        initializeData();
        //WHEN
        when(departmentMapper.convertToDto(entity)).thenReturn(dto);
        DepartmentDto actual = service.convertToDto(entity);
        //THEN
        verify(departmentMapper).convertToDto(entity);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    void shouldConvertDtoToEntity() {
        //GIVEN
        initializeData();
        //WHEN
        when(departmentMapper.convertToEntity(dto)).thenReturn(entity);
        Department actual = service.convertToEntity(dto);
        //THEN
        verify(departmentMapper).convertToEntity(dto);
        assertThat(actual).isEqualTo(entity);
    }

    private void initializeData() {
        entity = new Department(id, depName);
        dto = new DepartmentDto(id, depName);
    }
}