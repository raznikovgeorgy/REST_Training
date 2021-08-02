package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.model.Person;
import com.syncretis.rest_training.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class PersonController {

    private final PersonService service;

    @GetMapping("/persons")
    @ResponseBody
    public List<PersonDto> all() {
        return service.findAll();
    }

    @PostMapping("/persons")
    PersonDto newDocument(@RequestBody PersonDto newPerson) {
        return service.save(newPerson);
    }

    @GetMapping("/persons/{id}")
    PersonDto one(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/persons/{id}")
    PersonDto replaceDocument(@RequestBody PersonDto newPerson, @PathVariable Long id) {
        return service.update(id, newPerson);
    }

    @DeleteMapping("/persons/{id}")
    void deleteDocument(@PathVariable Long id) {
        service.delete(id);
    }
}