package com.estudandocomquestoes.controller;

import com.estudandocomquestoes.model.Subject;
import com.estudandocomquestoes.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questoes")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getAllQuestions(){
        return subjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Subject> getById(@PathVariable String id){
        return subjectRepository.findById(id);
    }

    @GetMapping("/filtro")
    public List<Subject> listBySubject(@RequestParam("subjectMatter") String subjectMatter){
        return new ArrayList<>(this.subjectRepository.findBySubjectMatter(subjectMatter));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subject addNewQuestion(@RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateQuestion(@PathVariable String id, @RequestBody Subject subject){
        if(!subjectRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        subject.setId(id);
        subject = subjectRepository.save(subject);
        return ResponseEntity.ok(subject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        if (!subjectRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        subjectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
