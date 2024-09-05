package com.login.login.Infrastructure.Repositories.Survey;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.login.Application.Services.ISurveyService;
import com.login.login.Domain.Surveys;


import jakarta.transaction.Transactional;

@Service
public class SurveyAdapter implements ISurveyService {

    @Autowired
    private SurveysRepository surveysRepository;


    @Override
    @Transactional
    public List<Surveys> findAll() {
        return surveysRepository.findAll();
    }

    @Override
    @Transactional
    public Surveys save(Surveys surveys) {
        return surveysRepository.save(surveys);
    }

    @Override
    @Transactional
    public Optional<Surveys> deleteById(Long id) {
        Optional<Surveys> sOptional = surveysRepository.findById(id);
        if (sOptional.isPresent()) {
            surveysRepository.deleteById(id);
        }
        return sOptional;
    }

    @Override
    @Transactional
    public Optional<Surveys> update(Long id, Surveys surveys) {
        Optional<Surveys> surveysOptional = surveysRepository.findById(id);
        if (surveysOptional.isPresent()) {
            Surveys surveysDB = surveysOptional.orElseThrow();
            surveysDB.setName(surveys.getName());
            surveysDB.setAudit(surveys.getAudit());
            surveysDB.setDescription(surveys.getDescription());
            surveysDB.setCategories(surveys.getCategories());

            return Optional.of(surveysRepository.save(surveysDB));
        }
        return surveysOptional;
    }

    @Override
    @Transactional
    public Optional<Surveys> findById(Long id) {
        // Consultan la base de datos y crean una intancia donde se almacena los datos
        // en JPA sOption ahi esta el dato que viene De JPA
        Optional<Surveys> sOptional = surveysRepository.findById(id);
        sOptional.ifPresent(db -> surveysRepository.findById(id));
        return sOptional;
    }

}

// @Override
// @Transactional
// public Optional<Surveys> update(Long id, Surveys surveys) {
// if (surveysRepository.existsById(id)) {
// surveys.setId(id);
// return Optional.of(surveysRepository.save(surveys));
// }
// return Optional.empty();
// }
// Optional<Surveys> sOptional = surveysRepository.findById(surveyId);
//         Optional<Categories> cOptional = categoryRepository.findById(surveyDTO.getCategories_id());


//           if (sOptional.isPresent() && cOptional.isPresent()) {
//             Surveys survey = sOptional.get();
//             Categories category = cOptional.get();

//             // Asigna la categoría a la encuesta
//             survey.setCategories(category);
//             surveysRepository.save(survey);
//         } else {
//             throw new RuntimeException("Encuesta o categoría no encontrada");
//         }
