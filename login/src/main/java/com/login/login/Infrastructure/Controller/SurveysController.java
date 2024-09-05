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
import com.login.login.Application.Services.ISurveyService;
import com.login.login.Domain.Categories;
import com.login.login.Domain.Surveys;
import com.login.login.Domain.DTO.SurveyDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/Surveys")
public class SurveysController {

    @Autowired
    private ISurveyService iSurveyService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/All")
    public List<Surveys> listSurveys() {
        return iSurveyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Surveys> sOptional = iSurveyService.findById(id);
        if (sOptional.isPresent()) {
           return ResponseEntity.ok(sOptional.orElseThrow());
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


    @PostMapping("/insertSurvey")
    public ResponseEntity<?> addCategoryToSurvey(@RequestBody SurveyDTO surveyDTO) {
        Long categoyId = surveyDTO.getCategories_id();
        Optional<Categories> cOptional = iCategoryService.findById(categoyId);

        if (cOptional.isPresent()) {
            Surveys surveys = new Surveys();

            surveys.setName(surveyDTO.getName());
            surveys.setDescription(surveyDTO.getDescription());
            surveys.setAudit(surveyDTO.getAudit());
            //Aca recuperamos el objeto-valor que esta almacenado en cOptional
            //Aca lo que hace es obtener el objeto Category y asignar o mandar uno
            surveys.setCategories(cOptional.get());
            Surveys createSurveys = iSurveyService.save(surveys);

            return ResponseEntity.ok(createSurveys);
            
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSurvey(@PathVariable Long id, @RequestBody SurveyDTO surveyDTO) {
    Optional<Surveys> sOptional = iSurveyService.findById(id);
    
    if (sOptional.isPresent()) {
        Surveys survey = sOptional.get();
        
        // Buscar la categoría correspondiente al categories_id recibido
        Long categoryId = surveyDTO.getCategories_id();
        Optional<Categories> cOptional = iCategoryService.findById(categoryId);
        
        if (cOptional.isPresent()) {
            
            
            // Actualizar los campos de la encuesta
            survey.setCategories(cOptional.get());
            survey.setName(surveyDTO.getName());
            survey.setDescription(surveyDTO.getDescription());
            
            // Guardar los cambios
            Surveys updatedSurvey = iSurveyService.save(survey);
            return ResponseEntity.ok(updatedSurvey);
        } else {
            // Si no se encuentra la categoría, devolver un error
            return ResponseEntity.badRequest().body("La categoría no existe");
        }
    } else {
        // Si no se encuentra la encuesta, devolver un error 404
        return ResponseEntity.notFound().build();
    }
}

}
