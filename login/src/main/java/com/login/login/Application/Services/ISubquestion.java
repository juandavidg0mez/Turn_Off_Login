package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;

import com.login.login.Domain.SubQuestion;

public interface ISubquestion {
    List<SubQuestion> findAll();

    SubQuestion save(SubQuestion subQuestion);

    Optional<SubQuestion> deleteById(Long id);

    Optional<SubQuestion> findByid(Long id);

    Optional<SubQuestion> update(Long id, SubQuestion subQuestion);

    void addSubQuestionTo(Long subid, Long QuestionId);
}
