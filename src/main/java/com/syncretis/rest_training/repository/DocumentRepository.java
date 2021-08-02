package com.syncretis.rest_training.repository;

import com.syncretis.rest_training.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, String> {
    List<Document> findAllByOrderByIdAsc();
}