package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
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
    LanguageDto newDocument(@Valid @RequestBody LanguageDto newLanguage) {
        return service.save(newLanguage);
    }

    @GetMapping("/languages/{id}")
    LanguageDto one(@PathVariable @Min(1) Long id) {
        return service.get(id);
    }

    @PutMapping("/languages/{id}")
    LanguageDto replaceDocument(@Valid @RequestBody LanguageDto newLanguage, @PathVariable @Min(1) Long id) {
        return service.update(id, newLanguage);
    }

    @DeleteMapping("/languages/{id}")
    void deleteDocument(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }
}