package com.syncretis.rest_training.repository;

import com.syncretis.rest_training.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findAllByOrderByIdAsc();
}