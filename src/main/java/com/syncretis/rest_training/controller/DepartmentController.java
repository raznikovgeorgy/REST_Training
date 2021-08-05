package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping("/departments")
    @ResponseBody
    public List<DepartmentDto> all() {
        return service.findAll();
    }

    @PostMapping("/departments")
    DepartmentDto newDepartment(@Valid @RequestBody DepartmentDto newDepartment) {
        return service.save(newDepartment);
    }

    @GetMapping("/departments/{id}")
    DepartmentDto one(@PathVariable @Min(1) Long id) {
        return service.get(id);
    }

    @PutMapping("/departments/{id}")
    DepartmentDto replaceDepartment( @Valid @RequestBody DepartmentDto newDepartment, @PathVariable @Min(1) Long id) {
        return service.update(id, newDepartment);
    }

    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }
}