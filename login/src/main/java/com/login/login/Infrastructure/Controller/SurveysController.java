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

import com.login.login.Application.Services.ISurveyService;
import com.login.login.Domain.Surveys;
import com.login.login.Domain.DTO.SurveyDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/Surveys")
public class SurveysController {

    @Autowired
    private ISurveyService iSurveyService;

    @GetMapping("/All")
    public List<Surveys> listSurveys() {
        return iSurveyService.findAll();
    }

    @GetMapping("/Survey/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Surveys> sOptional = iSurveyService.findById(id);
        if (sOptional.isPresent()) {
            ResponseEntity.ok(sOptional.orElseThrow());
        }
        // Recordemos que este Return es el Else If de la estructura de validacion por
        // eso mismo contruimos el error 400
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Surveys> sOptional = iSurveyService.findById(id);
        if (sOptional.isPresent()) {
            iSurveyService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();// El notfaund es un error 400 socio
        }

        // En este cvaso busca y hace una accion por eso es muy parecido o tama parte
        // del metodo buscar para ejecutar la accion
        // por otro lado el otro Servicio solo busca
    }

    @PostMapping("/NewSurveys")
    public ResponseEntity<?> create(@Valid @RequestBody Surveys surveys, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(iSurveyService.save(surveys)); // El notfaund es un error
                                                                                             // 400 socio
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Surveys surveys, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Surveys> sOptional = iSurveyService.findById(id);
        if (sOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/addCategory")
    public ResponseEntity<?> addCategoryToSurvey(@PathVariable Long id, @RequestBody SurveyDTO surveyDTO) {
        iSurveyService.addCategory(id, surveyDTO);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
