package com.login.login.Infrastructure.Repositories.Chapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.login.Application.Services.IChapterService;
import com.login.login.Domain.Chapters;

@Service
public class ChapterAdapter implements IChapterService {

    @Autowired
    private ChapterRespository chapterRespository;
    

    @Override
    public List<Chapters> findAll() {
        return chapterRespository.findAll();
    }

    @Override
    public Optional<Chapters> findById(Long id) {
        Optional<Chapters> cOptional = chapterRespository.findById(id);
        cOptional.ifPresent(db -> chapterRespository.findById(id));
        return cOptional;
    }

    @Override
    public Optional<Chapters> delete(Long id) {
        Optional<Chapters> cOptional = chapterRespository.findById(id);
        if (cOptional.isPresent()) {
            chapterRespository.deleteById(id);
        }
        return cOptional;
    }

    @Override
    public Optional<Chapters> update(Long id, Chapters chapters) {
        Optional<Chapters> cOptional = chapterRespository.findById(id);
        if (cOptional.isPresent()) {
            Chapters eChapters = new Chapters();

            eChapters.setChaper_number(chapters.getChaper_number());
            eChapters.setChaper_title(chapters.getChaper_title());
            eChapters.setAudit(chapters.getAudit());
            eChapters.setSurveys(chapters.getSurveys());

            return Optional.of(chapterRespository.save(eChapters));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Chapters savChapters(Chapters chapters) {
        return chapterRespository.save(chapters);
    }
}
