package com.estudandocomquestoes.repository;

import com.estudandocomquestoes.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {
    List<Subject> findBySubjectMatter(String subjectMatter);
    Optional<Subject> findById(String id);
}