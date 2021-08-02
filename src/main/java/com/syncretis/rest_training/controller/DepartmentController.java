package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    DepartmentDto newDepartment(@RequestBody DepartmentDto newDepartment) {
        return service.save(newDepartment);
    }

    @GetMapping("/departments/{id}")
    DepartmentDto one(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/departments/{id}")
    DepartmentDto replaceDepartment(@RequestBody DepartmentDto newDepartment, @PathVariable Long id) {
        return service.update(id, newDepartment);
    }

    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable Long id) {
        service.delete(id);
    }
}