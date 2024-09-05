package com.login.login.Infrastructure.Repositories.Question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.login.Application.Services.IQuestionService;
import com.login.login.Domain.Question;

@Service
public class QuestionAdapter implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> deleteById(Long id) {
        Optional<Question> qOptional = questionRepository.findById(id);
        if (qOptional.isPresent()) {
            questionRepository.deleteById(id);
        }
        return qOptional;
    }

    @Override
    public Optional<Question> pickOne(Long id) {
        Optional<Question> qOptional = questionRepository.findById(id);
        qOptional.ifPresent(db -> questionRepository.findById(id));
        return qOptional;
    }

    @Override
    public Optional<Question> update(Long id, Question question) {
        Optional<Question> qOptional = questionRepository.findById(id);
        if (qOptional.isPresent()) {
            Question eQuestion = qOptional.get();

            eQuestion.setQuestion_number(question.getQuestion_number());
            eQuestion.setResponse_type(question.getResponse_type());
            eQuestion.setCommet_question(question.getCommet_question());
            eQuestion.setQuestion_text(question.getQuestion_text());
            eQuestion.setChapters(question.getChapters());

            return Optional.of(questionRepository.save(eQuestion));
        } else {
            return Optional.empty(); // Retorna Optional vacío si la categoría no se encuentra
        }
    }



}
