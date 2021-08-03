package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.service.DocumentService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/")
    @ResponseBody
    public List<DocumentDto> all() {
        return service.findAll();
    }

    @PostMapping("")
    DocumentDto newDocument(@Valid @RequestBody DocumentDto newDocument) {
        return service.save(newDocument);
    }

    @GetMapping("/{id}")
    DocumentDto one(@Valid @PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    DocumentDto replaceDocument(@Valid @RequestBody DocumentDto newDocument, @Valid @PathVariable String id) {
        return service.update(id, newDocument);
    }

    @DeleteMapping("/{id}")
    void deleteDocument(@Valid @PathVariable String id) {
        service.delete(id);
    }
}