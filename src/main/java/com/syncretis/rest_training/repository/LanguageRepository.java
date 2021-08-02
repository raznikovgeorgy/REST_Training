package com.syncretis.rest_training.repository;

import com.syncretis.rest_training.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findAllByOrderByIdAsc();
}