package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.LanguageDto;
import com.syncretis.rest_training.model.Language;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LanguageMapperTest {
    private final String name = "RU";
    private final Long id = 1L;
    private final LanguageMapper languageMapper = new LanguageMapper();

    @Test
    void shouldConvertLanguageEntity() {
        //GIVEN
        LanguageDto dto = new LanguageDto(id, name);
        Language expected = new Language(name);
        expected.setId(id);
        //WHEN
        Language actual = languageMapper.convertToEntity(dto);
        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldConvertLanguageDto() {
        //GIVEN
        Language language = new Language(name);
        language.setId(id);
        LanguageDto expected = new LanguageDto(id, name);
        //WHEN
        LanguageDto actual = languageMapper.convertToDto(language);
        //THEN
        assertThat(actual).isEqualTo(expected);
    }
}