package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class LanguageController {

    private final LanguageService service;

    @GetMapping("/languages")
    @ResponseBody
    public List<LanguageDto> all() {
        return service.findAll();
    }

    @PostMapping("/languages")
    LanguageDto newDocument(@RequestBody LanguageDto newLanguage) {
        return service.save(newLanguage);
    }

    @GetMapping("/languages/{id}")
    LanguageDto one(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/languages/{id}")
    LanguageDto replaceDocument(@RequestBody LanguageDto newLanguage, @PathVariable Long id) {
        return service.update(id, newLanguage);
    }

    @DeleteMapping("/languages/{id}")
    void deleteDocument(@PathVariable Long id) {
        service.delete(id);
    }
}