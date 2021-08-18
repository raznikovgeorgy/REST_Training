package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.exception.LanguageNotFoundException;
import com.syncretis.rest_training.mapper.LanguageMapper;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@AllArgsConstructor
@Service
public class LanguageService {
    private LanguageRepository languageRepository;
    private LanguageMapper languageMapper;

    public void delete(@Min(1) Long id) {
        if (id == null || !isExist(id)) {
            throw new LanguageNotFoundException(id);
        } else {
            languageRepository.deleteById(id);
        }
    }

    public List<LanguageDto> findAll() {
        return languageRepository.findAllByOrderByIdAsc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LanguageDto get(@Min(1) Long id) {
        return convertToDto(languageRepository.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id)));
    }

    public LanguageDto save(@Valid LanguageDto dto) {
        return convertToDto(languageRepository.save(convertToEntity(dto)));
    }

    public LanguageDto update(@Min(1) Long id, @Valid LanguageDto dto) {
        dto.setId(id);
        return convertToDto(languageRepository.save(convertToEntity(dto)));
    }

    public LanguageDto convertToDto(Language l) {
        return languageMapper.convertToDto(l);
    }

    public Language convertToEntity(LanguageDto dto) {
        return languageMapper.convertToEntity(dto);
    }

    public boolean isExist(Long id) {
        return languageRepository.existsById(id);
    }
}