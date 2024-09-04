package com.login.login.Infrastructure.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.login.Application.Services.ICategoryService;
import com.login.login.Domain.Categories;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth/Category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;
    // Estos son metodos que van ha ahcer invocaods posteriormente
    // Recordemos el tipo de Metodo que debemos utilizar a la hora consumir el Servicio
    @GetMapping("/All")
    public List<Categories> listCategories(){
        return iCategoryService.findAll();
    } 

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        // Es esta caso aca busca del servicio y gual ese resultado en cOptional para luego manipularlo
        Optional<Categories> cOptional = iCategoryService.findById(id);
        if (cOptional.isPresent()) {
            return ResponseEntity.ok(cOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<Categories> cOptional = iCategoryService.findById(id);
        if (cOptional.isPresent()) {
            iCategoryService.deteleById(id);
            return ResponseEntity.ok().build(); // Devuelve 200 OK sin cuerpo
        } else {
            return ResponseEntity.notFound().build();// El notfaund es un error 400 socio
        }
    }

    @PostMapping("/NewCategory")
    public ResponseEntity<?> create(@Valid @RequestBody Categories categories, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);

        }
         return ResponseEntity.status(HttpStatus.CREATED).body(iCategoryService.save(categories));// El notfaund es un error 400 socio
    }

    @PutMapping("/Put/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Categories categories, BindingResult result, @PathVariable Long id){

        if(result.hasFieldErrors()){
            return validation(result);

        }

        Optional<Categories> cOptional = iCategoryService.update(id, categories);
        if (cOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();// El notfaund es un error 400 socio
    }

    @PostMapping("/{categoryId}/surveys/{surveyId}")
    public ResponseEntity<?> addSurveyToCategory(@PathVariable Long categoryId, @PathVariable Long surveyId) {
        try {
            iCategoryService.addCategory(surveyId, categoryId);
            return ResponseEntity.ok("Encuesta agregada a la categoría.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }



}
