package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;

import com.login.login.Domain.SubQuestion;
import com.login.login.Domain.DTO.SubquetionDTO;

public interface ISubquestion {
    List<SubQuestion> findAll();

    SubQuestion save(SubquetionDTO subquetionDTO);

    Optional<SubQuestion> deleteById(Long id);

    Optional<SubQuestion> findByid(Long id);

    void update(Long id, SubquetionDTO subquetionDTO);

}
