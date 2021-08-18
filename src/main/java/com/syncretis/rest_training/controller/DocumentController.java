package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService service;

    @GetMapping("")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<DocumentDto> all() {
        return service.findAll();
    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    DocumentDto newDocument(@Valid @RequestBody DocumentDto newDocument) {
        return service.save(newDocument);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    DocumentDto one(@Valid @PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    DocumentDto replaceDocument(@Valid @RequestBody DocumentDto newDocument, @Valid @PathVariable String id) {
        return service.update(id, newDocument);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    void deleteDocument(@Valid @PathVariable String id) {
        service.delete(id);
    }
}