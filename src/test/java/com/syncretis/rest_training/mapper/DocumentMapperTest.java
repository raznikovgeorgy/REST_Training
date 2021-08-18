package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.model.Document;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentMapperTest {
    private final static String id = "hv62eryjmyl6j4ds36a24t64l26s5ag426";
    private final static LocalDate date = LocalDate.of(2077, 1, 1);
    private final DocumentMapper documentMapper = new DocumentMapper();

    @Test
    void shouldConvertDocumentEntity() {
        //GIVEN
        DocumentDto dto = new DocumentDto(id, date);
        Document expected = new Document(id, date);
        //WHEN
        Document actual = documentMapper.convertToEntity(dto);
        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldConvertDocumentDto() {
        //GIVEN
        Document document = new Document(id, date);
        DocumentDto expected = new DocumentDto(id, date);
        //WHEN
        DocumentDto actual = documentMapper.convertToDto(document);
        //THEN
        assertThat(actual).isEqualTo(expected);
    }
}