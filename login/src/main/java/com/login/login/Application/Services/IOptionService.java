package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;


import com.login.login.Domain.Option_Question;
import com.login.login.Domain.DTO.OptionsDTO;

public interface IOptionService {
    List<Option_Question> findAll();
    Option_Question saveForQuestion(OptionsDTO optionsDTO);
    Option_Question saveForSubQuestion(OptionsDTO optionsDTO);
    Optional<Option_Question> deleteById(Long id);
    Optional<Option_Question> findById(Long id);
    void update(Long id, OptionsDTO optionsDTO);
}   
