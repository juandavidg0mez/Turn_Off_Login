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

import com.login.login.Application.Services.IOptionService;
import com.login.login.Domain.Option_Question;
import com.login.login.Domain.DTO.OptionsDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/Option")
public class OptionController {
    @Autowired
    private IOptionService iOptionService;

   

    @PutMapping("/update/{id}")
    public void updateOptionQuestion(@Valid @RequestBody OptionsDTO optionsDTO, @PathVariable Long id) {
        try {

            iOptionService.update(id, optionsDTO);

        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    // @PostMapping("/create")
    // public ResponseEntity<?> createOptionQuestion(@Valid @RequestBody OptionsDTO optionsDTO) {
    //     try {
    //         Option_Question createdOption = iOptionService.save(optionsDTO);
    //         return ResponseEntity.ok(createdOption);
    //     } catch (EntityNotFoundException e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    //     }
    // }
    @PostMapping("/createForQuestion")
    public ResponseEntity<?> createOptionForQuestion(@Valid @RequestBody OptionsDTO optionsDTO) {
        try {
            Option_Question createdOption = iOptionService.saveForQuestion(optionsDTO);
            return ResponseEntity.ok(createdOption);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/createForSubQuestion")
    public ResponseEntity<?> createOptionForSubQuestion(@Valid @RequestBody OptionsDTO optionsDTO) {
        try {
            Option_Question createdOption = iOptionService.saveForSubQuestion(optionsDTO);
            return ResponseEntity.ok(createdOption);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/All")
    public List<Option_Question> findAll(){
        return iOptionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Option_Question> cOptional = iOptionService.findById(id);
        if (cOptional.isPresent()) {
            return ResponseEntity.ok(iOptionService.findById(id));
            
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/Detele/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<Option_Question> cOptional = iOptionService.findById(id);
        if (cOptional.isPresent()) {
            iOptionService.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
