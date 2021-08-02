package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.exception.LanguageNotFoundException;
import com.syncretis.rest_training.mapper.LanguageMapper;
import com.syncretis.rest_training.model.Language;
import com.syncretis.rest_training.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LanguageService {

    private LanguageRepository languageRepository;

    private LanguageMapper languageMapper;

    public void delete(Long id) {
        if (id != null) {
            languageRepository.deleteById(id);
        }
    }

    public List<LanguageDto> findAll() {
        return languageRepository.findAllByOrderByIdAsc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LanguageDto get(Long id) {
        return convertToDto(languageRepository.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id)));
    }

    public LanguageDto save(LanguageDto dto) {
        return convertToDto(languageRepository.save(convertToEntity(dto)));
    }

    public LanguageDto update(Long id, LanguageDto dto) {
        dto.setId(id);
        return convertToDto(languageRepository.save(convertToEntity(dto)));
    }

    public LanguageDto convertToDto(Language l) {
        return languageMapper.convert(l);
    }

    public Language convertToEntity(LanguageDto dto) {
        return languageMapper.convert(dto);
    }
}