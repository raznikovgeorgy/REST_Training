package com.syncretis.rest_training.mapper;

import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.model.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public Document convertToEntity(DocumentDto dto) {
        Document result = new Document();
        result.setId(dto.getId());
        result.setExpireDate(dto.getExpireDate());
        return result;
    }

    public DocumentDto convertToDto(Document doc) {
        DocumentDto result = new DocumentDto();
        result.setId(doc.getId());
        result.setExpireDate(doc.getExpireDate());
        return result;
    }
}