package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.service.PersonService;
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
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService service;

    @GetMapping("")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<PersonDto> all() {
        return service.findAll();
    }

//    if you want batch save instead single entity save, just uncomment this method and comment method down below
//    @PostMapping("/persons")
//    List<PersonDto> newDocuments(@RequestBody List<PersonDto> newPersons) {
//        return service.saveAll(newPersons);
//    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    PersonDto newDocument(@Valid @RequestBody PersonDto newPerson) {
        return service.save(newPerson);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    PersonDto one(@PathVariable @Min(1) Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    PersonDto replaceDocument(@Valid @RequestBody PersonDto newPerson, @PathVariable @Min(1) Long id) {
        return service.update(id, newPerson);
    }

    @DeleteMapping("/{id}")
    void deleteDocument(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }

    @DeleteMapping("")
    @Secured("ROLE_ADMIN")
    void deleteDocuments(@RequestBody List<PersonDto> personsToDelete) {
        service.deleteAll(personsToDelete);
    }
}