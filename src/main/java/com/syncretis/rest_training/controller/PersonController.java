package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//    @PostMapping("/persons")
//    PersonDto newDocument(@RequestBody PersonDto newPerson) {
//        if (newPerson.getName() == null)
//            throw new PersonNotValidDataException();
//        return service.save(newPerson);
//    }

    @PostMapping("/persons")
    List<PersonDto> newDocuments(@RequestBody List<PersonDto> newPersons) {
        return service.saveAll(newPersons);
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

    @DeleteMapping("/persons")
    void deleteDocuments(@RequestBody List<PersonDto> personsToDelete) {
        service.deleteAll(personsToDelete);
    }
}