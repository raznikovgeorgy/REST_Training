package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.exception.personException.PersonNotValidDataException;
import com.syncretis.rest_training.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
public class PersonController {

    private final PersonService service;

    @GetMapping("/persons")
    @ResponseBody
    public List<PersonDto> all() {
        return service.findAll();
    }

    //if you want single save instead batch save, just uncomment this method and comment method down below
    @PostMapping("/persons")
    PersonDto newDocument(@Valid @RequestBody PersonDto newPerson) {
        return service.save(newPerson);
    }

//    @PostMapping("/persons")
//    List<PersonDto> newDocuments(@RequestBody List<PersonDto> newPersons) {
//        return service.saveAll(newPersons);
//    }

    @GetMapping("/persons/{id}")
    PersonDto one(@PathVariable @Min(1) Long id) {
        return service.get(id);
    }

    @PutMapping("/persons/{id}")
    PersonDto replaceDocument(@Valid @RequestBody PersonDto newPerson, @PathVariable @Min(1) Long id) {
        return service.update(id, newPerson);
    }

    @DeleteMapping("/persons/{id}")
    void deleteDocument(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }

    @DeleteMapping("/persons")
    void deleteDocuments(@RequestBody List<PersonDto> personsToDelete) {
        service.deleteAll(personsToDelete);
    }
}