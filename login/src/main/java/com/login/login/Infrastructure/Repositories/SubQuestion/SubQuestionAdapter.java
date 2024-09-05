package com.login.login.Infrastructure.Repositories.SubQuestion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.login.Application.Services.ISubquestion;
import com.login.login.Domain.Question;
import com.login.login.Domain.SubQuestion;
import com.login.login.Domain.DTO.SubquetionDTO;
import com.login.login.Infrastructure.Repositories.Question.QuestionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SubQuestionAdapter implements ISubquestion {

    @Autowired
    private SubQuestionRepository subQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<SubQuestion> findAll() {
        return subQuestionRepository.findAll();
    }

    @Override
    public Optional<SubQuestion> deleteById(Long id) {
        Optional<SubQuestion> SubQuestionOptional = subQuestionRepository.findById(id);

        if (SubQuestionOptional.isPresent()) {
            subQuestionRepository.deleteById(id);
        }
        return SubQuestionOptional;
    }

    @Override
    public Optional<SubQuestion> findByid(Long id) {
        Optional<SubQuestion> SubQuestionOptional = subQuestionRepository.findById(id);
        SubQuestionOptional.ifPresent(db -> subQuestionRepository.findById(id));
        return SubQuestionOptional;
    }

    @Override
    public void update(Long id, SubquetionDTO subquetionDTO) {

        Optional<SubQuestion> existingSubQuestion = subQuestionRepository.findById(id);

        // Buscar la pregunta relacionada por ID desde el DTO

        if (existingSubQuestion.isPresent()) {
            // Actualizar los campos de la subpregunta existente
            SubQuestion subQuestion = existingSubQuestion.get();

            Long questionId = subquetionDTO.getQuestion_id();

            Optional<Question> questionOptional = questionRepository.findById(questionId);

            if (questionOptional.isPresent()) {
                subQuestion.setSubquestiontext(subquetionDTO.getSubquestiontext());
                subQuestion.setAudit(subquetionDTO.getAudit());
                subQuestion.setQuestion(questionOptional.get());

                SubQuestion create = subQuestionRepository.save(subQuestion);
                Optional.of(create);
            }
        }
        Optional.empty();
    }

    @Override
    public SubQuestion save(SubquetionDTO subquetionDTO) {
        Long question_id = subquetionDTO.getQuestion_id();
        Optional<Question> SubOptional = questionRepository.findById(question_id);
        if (SubOptional.isPresent()) {

            SubQuestion subQuestion = new SubQuestion();
            subQuestion.setSubquestiontext(subquetionDTO.getSubquestiontext());
            subQuestion.setAudit(subquetionDTO.getAudit());
            subQuestion.setQuestion(SubOptional.get());

            return subQuestionRepository.save(subQuestion);

        } else {
            throw new EntityNotFoundException("Question with ID " + question_id + " not found");
        }
    }

}
