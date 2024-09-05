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

import com.login.login.Application.Services.IChapterService;
import com.login.login.Application.Services.ISurveyService;
import com.login.login.Domain.Chapters;
import com.login.login.Domain.Surveys;
import com.login.login.Domain.DTO.ChapterDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/Chapter")
public class ChapterController {

    @Autowired
    private IChapterService iChapterService;

    @Autowired
    private ISurveyService iSurveyService;

    @GetMapping("/All")
    public List<Chapters> listChapters(){
        return iChapterService.findAll();
    }

    @PostMapping("/Create")
    public ResponseEntity<?> create(@RequestBody ChapterDTO chapterDTO){
        Long surveyId = chapterDTO.getSurveid();
        Optional<Surveys> cOptional = iSurveyService.findById(surveyId);
        if (cOptional.isPresent()) {
            
            Chapters chapters = new Chapters();

            chapters.setChaper_number(chapterDTO.getChaper_number());
            chapters.setChaper_title(chapterDTO.getChaper_title());
            chapters.setAudit(chapterDTO.getAudit());
            chapters.setSurveys(cOptional.get());

            Chapters create = iChapterService.savChapters(chapters);
            
            return ResponseEntity.ok(create);

        }else{

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid  @RequestBody ChapterDTO chapterDTO, @PathVariable long id ){
        Optional<Chapters> sOptional = iChapterService.findById(id);

        if (sOptional.isPresent()) {

            Chapters chapters = sOptional.get();

            Long surveyId = chapterDTO.getSurveid();
            Optional<Surveys> cOptional = iSurveyService.findById(surveyId);

            if (cOptional.isPresent()) {
                chapters.setSurveys(cOptional.get());
                chapters.setAudit(chapterDTO.getAudit());
                chapters.setChaper_number(chapterDTO.getChaper_number());
                chapters.setChaper_title(chapterDTO.getChaper_title());

                Chapters create = iChapterService.savChapters(chapters);
                return ResponseEntity.ok(create);

            }else{
                return ResponseEntity.badRequest().body("La encuesta no existe");
            }
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Chapters> cOptional = iChapterService.findById(id);
        if (cOptional.isPresent()) {
            return ResponseEntity.ok(iChapterService.findById(id));
            
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/Detele/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<Chapters> cOptional = iChapterService.findById(id);
        if (cOptional.isPresent()) {
            iChapterService.delete(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    

}
