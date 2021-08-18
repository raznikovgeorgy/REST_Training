package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.model.Department;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentMapperTest {
    private final static Long id = 1L;
    private final static String name = "Department of Good Music";
    private final DepartmentMapper departmentMapper = new DepartmentMapper();

    @Test
    void shouldConvertDepartmentEntity() {
        //GIVEN
        DepartmentDto dto = new DepartmentDto(id, name);
        Department expected = new Department(id, name);
        //WHEN
        Department actual = departmentMapper.convertToEntity(dto);
        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldConvertDepartmentDto() {
        //GIVEN
        Department department = new Department(id, name);
        DepartmentDto expected = new DepartmentDto(id, name);
        //WHEN
        DepartmentDto actual = departmentMapper.convertToDto(department);
        //THEN
        assertThat(actual).isEqualTo(expected);
    }
}