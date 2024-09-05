package com.login.login.Infrastructure.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.login.login.Application.Services.IChapterService;
import com.login.login.Application.Services.IQuestionService;
import com.login.login.Domain.Chapters;
import com.login.login.Domain.Question;
import com.login.login.Domain.DTO.QuestionDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/Question")
public class QuestionController {
    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private IChapterService iChapterService;

    @GetMapping("/All")
    public List<Question> findAll() {
        return iQuestionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Question> qOptional = iQuestionService.pickOne(id);
        if (qOptional.isPresent()) {
            return ResponseEntity.ok(qOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Question> qOptional = iQuestionService.pickOne(id);
        if (qOptional.isPresent()) {
            iQuestionService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insertCreate")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        Long chapterId = questionDTO.getChapter_id();
        Optional<Chapters> cOptional = iChapterService.findById(chapterId);

        if (cOptional.isPresent()) {

            Question question = new Question();

            question.setQuestion_number(questionDTO.getQuestion_number());
            question.setQuestion_text(questionDTO.getQuestion_text());
            question.setResponse_type(questionDTO.getResponse_type());
            question.setCommet_question(questionDTO.getCommet_question());
            question.setAudit(questionDTO.getAudit());
            question.setChapters(cOptional.get());

            Question createQuestion = iQuestionService.save(question);

            return ResponseEntity.ok(createQuestion);

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update/{id}")
public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody QuestionDTO questionDTO) {
    Optional<Question> sOptional = iQuestionService.pickOne(id);
    if (sOptional.isPresent()) {
        Question question = sOptional.get();
        
        // Actualiza los campos de la entidad Question con los valores del DTO
        question.setQuestion_number(questionDTO.getQuestion_number());
        question.setResponse_type(questionDTO.getResponse_type());
        question.setCommet_question(questionDTO.getCommet_question());
        question.setQuestion_text(questionDTO.getQuestion_text());
        
        // Actualiza la relación con el capítulo
        Optional<Chapters> cOptional = iChapterService.findById(questionDTO.getChapter_id());
        if (cOptional.isPresent()) {
            question.setChapters(cOptional.get());
        } else {
            return ResponseEntity.badRequest().body("Chapter not found");
        }

        // Guarda la entidad actualizada
        Question updatedQuestion = iQuestionService.save(question);
        
        return ResponseEntity.ok(updatedQuestion);
    }
    return ResponseEntity.notFound().build();
}


}
