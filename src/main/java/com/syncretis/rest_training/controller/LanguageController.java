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
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService service;

    @GetMapping("")
    @ResponseBody
    public List<LanguageDto> all() {
        return service.findAll();
    }

    @PostMapping("")
    LanguageDto newDocument(@Valid @RequestBody LanguageDto newLanguage) {
        return service.save(newLanguage);
    }

    @GetMapping("/{id}")
    LanguageDto one(@PathVariable @Min(1) Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    LanguageDto replaceDocument(@Valid @RequestBody LanguageDto newLanguage, @PathVariable @Min(1) Long id) {
        return service.update(id, newLanguage);
    }

    @DeleteMapping("/{id}")
    void deleteDocument(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }
}