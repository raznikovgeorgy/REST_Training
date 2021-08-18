package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.exception.DocumentNotFoundException;
import com.syncretis.rest_training.mapper.DocumentMapper;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@AllArgsConstructor
public class DocumentService {
    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;

    public void delete(@Pattern(regexp = "[A-Za-z0-9]{32}") String id) {
        if (id == null || !isExist(id)) {
            throw new DocumentNotFoundException(id);
        } else {
            documentRepository.deleteById(id);
        }
    }

    public List<DocumentDto> findAll() {
        return documentRepository.findAllByOrderByIdAsc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DocumentDto get(@Pattern(regexp = "[A-Za-z0-9]{32}") String id) {
        return convertToDto(documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id)));
    }

    public DocumentDto save(@Valid DocumentDto dto) {
        return convertToDto(documentRepository.save(convertToEntity(dto)));
    }

    public DocumentDto update(@Pattern(regexp = "[A-Za-z0-9]{32}") String id, @Valid DocumentDto dto) {
        dto.setId(id);
        return convertToDto(documentRepository.save(convertToEntity(dto)));
    }

    public DocumentDto convertToDto(Document document) {
        return documentMapper.convertToDto(document);
    }

    public Document convertToEntity(DocumentDto dto) {
        return documentMapper.convertToEntity(dto);
    }

    public boolean isExist(String id) {
        return documentRepository.existsById(id);
    }
}