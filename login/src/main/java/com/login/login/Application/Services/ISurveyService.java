package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;

import com.login.login.Domain.Surveys;
import com.login.login.Domain.DTO.SurveyDTO;

public interface ISurveyService 
{
    List<Surveys> findAll();

    Surveys save(Surveys surveys);

    Optional<Surveys> deleteById(Long id);

    Optional<Surveys> update(Long id, Surveys surveys);

    Optional<Surveys> findById(Long id);

    void addCategory(Long surveysId, SurveyDTO surveyDTO);

}
