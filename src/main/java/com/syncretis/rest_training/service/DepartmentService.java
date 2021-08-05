package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.exception.DepartmentNotFoundException;
import com.syncretis.rest_training.exception.personException.PersonNotFoundException;
import com.syncretis.rest_training.mapper.DepartmentMapper;
import com.syncretis.rest_training.model.Department;
import com.syncretis.rest_training.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private DepartmentMapper departmentMapper;

    public void delete(@Min(1) Long id) {
        if (id == null || isExist(id)) {
            throw new DepartmentNotFoundException(id);
        } else {
            departmentRepository.deleteById(id);
        }
    }

    public List<DepartmentDto> findAll() {
        return departmentRepository.findAllByOrderByIdAsc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DepartmentDto get(@Min(1) Long id) {
        return convertToDto(departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id)));
    }

    public DepartmentDto save(@Valid DepartmentDto dto) {
        return convertToDto(departmentRepository.save(convertToEntity(dto)));
    }

    public DepartmentDto update(@Min(1) Long id, @Valid DepartmentDto dto) {
        dto.setId(id);
        return convertToDto(departmentRepository.save(convertToEntity(dto)));
    }

    public DepartmentDto convertToDto(Department d) {
        return departmentMapper.convert(d);
    }

    public Department convertToEntity(DepartmentDto departmentDto) {
        return departmentMapper.convert(departmentDto);
    }

    public boolean isExist(Long id) {
        return departmentRepository.existsById(id);
    }

}