package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.model.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public Language convert(LanguageDto dto) {
        Language result = new Language();
        result.setId(dto.getId());
        result.setName(dto.getName());
        return result;
    }

    public LanguageDto convert(Language lang) {
        LanguageDto result = new LanguageDto();
        result.setId(lang.getId());
        result.setName(lang.getName());
        return result;
    }
}