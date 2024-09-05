package com.login.login.Infrastructure.Repositories.Option;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.login.Application.Services.IOptionService;
import com.login.login.Domain.Option_Question;
import com.login.login.Domain.Question;
import com.login.login.Domain.SubQuestion;
import com.login.login.Domain.DTO.OptionsDTO;
import com.login.login.Infrastructure.Repositories.Question.QuestionRepository;
import com.login.login.Infrastructure.Repositories.SubQuestion.SubQuestionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Optionadapter implements IOptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private SubQuestionRepository subQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Option_Question> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public Option_Question save(OptionsDTO optionsDTO) {
        Long question_id = optionsDTO.getQuestion_id();
        Optional<Question> opQuestion = questionRepository.findById(question_id);

        Long subQuestion_id = optionsDTO.getSubQuestion_id();
        Optional<SubQuestion> opSubQuestion = subQuestionRepository.findById(subQuestion_id);

        if (opQuestion.isPresent() || opSubQuestion.isPresent()) {
            Option_Question option_Question = new Option_Question();
            option_Question.setOptiontext(optionsDTO.getOptiontext());
            option_Question.setAudit(optionsDTO.getAudit());
    
            // Asignar la pregunta si está presente
            if (opQuestion.isPresent()) {
                option_Question.setQuestion(opQuestion.get());
            }
    
            // Asignar la subpregunta si está presente
            if (opSubQuestion.isPresent()) {
                option_Question.setSubQuestion(opSubQuestion.get());
            }
    
            return optionRepository.save(option_Question);
        } else {
            throw new EntityNotFoundException("Debe proporcionar un ID válido para una pregunta o una subpregunta.");
        }

    }

    @Override
    public void update(Long id, OptionsDTO optionsDTO) {
        // Buscar si es una SubQuestion
        Optional<SubQuestion> existingSubQuestion = subQuestionRepository.findById(id);

        // Buscar si es una Question
        Optional<Question> existingQuestion = questionRepository.findById(id);

        // Actualización si es SubQuestion
        if (existingSubQuestion.isPresent()) {
            SubQuestion subQuestion = existingSubQuestion.get();

            // Actualizar el texto de la subpregunta con el texto de la opción
            subQuestion.setSubquestiontext(optionsDTO.getOptiontext());

            // Actualizar los campos de auditoría si es necesario
            subQuestion.setAudit(optionsDTO.getAudit());

            subQuestionRepository.save(subQuestion);

        } else if (existingQuestion.isPresent()) {
            // Actualización si es Question
            Question question = existingQuestion.get();

            
            question.setQuestion_text(optionsDTO.getOptiontext());

            
            question.setAudit(optionsDTO.getAudit());

            
            questionRepository.save(question);
        } else {
            // Si no se encuentra ni SubQuestion ni Question
            throw new EntityNotFoundException("No se encontró ninguna pregunta o subpregunta con el ID proporcionado.");
        }

    }

    @Override
    public Optional<Option_Question> deleteById(Long id) {
        Optional<Option_Question> OptionalOption = optionRepository.findById(id);
        if (OptionalOption.isPresent()) {
            optionRepository.deleteById(id);
        }
        return OptionalOption;
    }

    @Override
    public Optional<Option_Question> findById(Long id) {
        Optional<Option_Question> OptionalOption = optionRepository.findById(id);
        OptionalOption.ifPresent(db -> optionRepository.findById(id));
        return OptionalOption;
    }

}
