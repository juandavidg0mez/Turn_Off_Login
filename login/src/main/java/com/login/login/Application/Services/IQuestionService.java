package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;

import com.login.login.Domain.Question;


public interface IQuestionService {

    List<Question> findAll();

    Question save(Question question);

    Optional<Question> deleteById(Long id);

    Optional<Question> pickOne(Long id);

    Optional<Question> update(Long id, Question question);

}
