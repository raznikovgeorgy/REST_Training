package com.syncretis.rest_training.repository;

import com.syncretis.rest_training.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByOrderByIdAsc();

    void deleteAllInBatch(List<Person> collect);
}