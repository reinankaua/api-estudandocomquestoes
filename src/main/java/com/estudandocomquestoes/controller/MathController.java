package com.estudandocomquestoes.controller;

import com.estudandocomquestoes.model.Math;
import com.estudandocomquestoes.repository.MathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MathController {

    @Autowired
    private MathRepository mathRepository;

    @GetMapping
    public String apresentetion(){
        return "Olá, Tudo bem?<br>" +
                "Temos questões disponiveis para MATEMÁTICA...<br><br>" +
                "Para acessar as questões de MATEMÁTICA adicione '/matematica' na URL";
    }

    @GetMapping("/matematica")
    public List<Math> listAllQuestions(){
        return mathRepository.findAll();
    }

    @GetMapping("/matematica/{id}")
    public Optional<Math> listById(@PathVariable String id){
        return mathRepository.findById(id);
    }

    @GetMapping("/matematica/filter")
    public List<Math> listBySubject(@RequestParam("subject") String subject){
        List<Math> collect = new ArrayList<>(this.mathRepository.findBySubject(subject));
        return collect;
    }

    @PostMapping("/matematica")
    @ResponseStatus(HttpStatus.CREATED)
    public Math addQuestion(@RequestBody Math math){
        return mathRepository.save(math);
    }

    @PutMapping("matematica/{id}")
    public ResponseEntity<Math> update(@PathVariable String id, @RequestBody Math math){

        if(!mathRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        math.setId(id);
        math = mathRepository.save(math);
        return ResponseEntity.ok(math);
    }

    @DeleteMapping("matematica/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){

        if(!mathRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        mathRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
