package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department convert(DepartmentDto dto) {
        Department result = new Department();
        result.setId(dto.getId());
        result.setName(dto.getName());
        return result;
    }

    public DepartmentDto convert(Department dep) {
        DepartmentDto result = new DepartmentDto();
        result.setId(dep.getId());
        result.setName(dep.getName());
        return result;
    }
}