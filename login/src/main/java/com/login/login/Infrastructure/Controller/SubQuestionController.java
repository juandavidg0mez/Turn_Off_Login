package com.login.login.Infrastructure.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.login.Application.Services.ISubquestion;
import com.login.login.Domain.SubQuestion;
import com.login.login.Domain.DTO.SubquetionDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/Subquestion")
public class SubQuestionController {
    @Autowired
    private ISubquestion iSubquestion;

    @PostMapping("/create")
    public ResponseEntity<?> createSubQuestion(@Valid @RequestBody SubquetionDTO subquetionDTO) {
        try {
    
            SubQuestion newSubQuestion = iSubquestion.save(subquetionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newSubQuestion);  // Devuelve la subpregunta creada con un código 201 (CREATED)
        } catch (EntityNotFoundException e) {
            // Manejo de error 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public void updateSubQuestion( @Valid @RequestBody SubquetionDTO subquetionDTO,@PathVariable Long id) {
        try {
            iSubquestion.update(id, subquetionDTO);  
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @GetMapping("/All")
    public List<SubQuestion> listChapters(){
        return iSubquestion.findAll();
    }

     @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<SubQuestion> cOptional = iSubquestion.findByid(id);
        if (cOptional.isPresent()) {
            return ResponseEntity.ok(iSubquestion.findByid(id));
            
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/Detele/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<SubQuestion> cOptional = iSubquestion.findByid(id);
        if (cOptional.isPresent()) {
            iSubquestion.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
