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
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping("")
    @ResponseBody
    public List<DepartmentDto> all() {
        return service.findAll();
    }

    @PostMapping("")
    DepartmentDto newDepartment(@Valid @RequestBody DepartmentDto newDepartment) {
        return service.save(newDepartment);
    }

    @GetMapping("{id}")
    DepartmentDto one(@PathVariable @Min(1) Long id) {
        return service.get(id);
    }

    @PutMapping("{id}")
    DepartmentDto replaceDepartment( @Valid @RequestBody DepartmentDto newDepartment, @PathVariable @Min(1) Long id) {
        return service.update(id, newDepartment);
    }

    @DeleteMapping("{id}")
    void deleteDepartment(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }
}