package com.syncretis.rest_training.service;

import com.syncretis.rest_training.dto.DepartmentDto;
import com.syncretis.rest_training.dto.DocumentDto;
import com.syncretis.rest_training.exception.DepartmentNotFoundException;
import com.syncretis.rest_training.exception.DocumentNotFoundException;
import com.syncretis.rest_training.mapper.DocumentMapper;
import com.syncretis.rest_training.model.Document;
import com.syncretis.rest_training.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService {

    private DocumentRepository documentRepository;

    private DocumentMapper documentMapper;

    public void delete(String id) {
        if (id != null) {
            documentRepository.deleteById(id);
        }
    }

    public List<DocumentDto> findAll() {
        return documentRepository.findAllByOrderByIdAsc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DocumentDto get(String id) {
        return convertToDto(documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id)));
    }

    public DocumentDto save(DocumentDto dto) {
        return convertToDto(documentRepository.save(convertToEntity(dto)));
    }

    public DocumentDto update(String id, DocumentDto dto) {
        dto.setId(id);
        return convertToDto(documentRepository.save(convertToEntity(dto)));
    }

    public DocumentDto convertToDto(Document document) {
        return documentMapper.convert(document);
    }

    public Document convertToEntity(DocumentDto dto) {
        return documentMapper.convert(dto);
    }
}