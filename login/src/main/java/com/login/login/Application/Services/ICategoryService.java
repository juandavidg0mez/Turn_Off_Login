package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;

import com.login.login.Domain.Categories;


public interface ICategoryService {

    List<Categories> findAll();

    Categories save(Categories category);

    Optional<Categories> deteleById(Long id);

    Optional<Categories> findById(Long id);

    Optional<Categories> update(Long id, Categories category);
    
}
