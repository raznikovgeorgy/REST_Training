package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<DepartmentDto> all() {
        return service.findAll();
    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    DepartmentDto newDepartment(@Valid @RequestBody DepartmentDto newDepartment) {
        return service.save(newDepartment);
    }

    @GetMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    DepartmentDto one(@PathVariable @Min(1) Long id) {
        return service.get(id);
    }

    @PutMapping("{id}")
    @Secured("ROLE_ADMIN")
    DepartmentDto replaceDepartment( @Valid @RequestBody DepartmentDto newDepartment, @PathVariable @Min(1) Long id) {
        return service.update(id, newDepartment);
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    void deleteDepartment(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }
}