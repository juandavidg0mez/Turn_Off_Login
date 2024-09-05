package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;

import com.login.login.Domain.Chapters;
public interface IChapterService {
    List<Chapters> findAll();
    Chapters savChapters(Chapters chapters);
    Optional<Chapters> findById(Long id);
    Optional<Chapters> delete(Long id);
    Optional<Chapters> update(Long id, Chapters chapters);
}
