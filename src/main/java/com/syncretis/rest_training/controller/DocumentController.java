package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class DocumentController {

    private final DocumentService service;

    @GetMapping("/documents")
    @ResponseBody
    public List<DocumentDto> all() {
        return service.findAll();
    }

    @PostMapping("/documents")
    DocumentDto newDocument(@RequestBody DocumentDto newDocument) {
        return service.save(newDocument);
    }

    @GetMapping("/documents/{id}")
    DocumentDto one(@PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/documents/{id}")
    DocumentDto replaceDocument(@RequestBody DocumentDto newDocument, @PathVariable String id) {
        return service.update(id, newDocument);
    }

    @DeleteMapping("/documents/{id}")
    void deleteDocument(@PathVariable String id) {
        service.delete(id);
    }
}