package com.login.login.Application.Services;

import java.util.List;
import java.util.Optional;

import com.login.login.Domain.Option;

public interface IOptionService {
    List<Option> findAll();
    Option save(Option option);
    Optional<Option> deleteById(Long id);
    Optional<Option> findById(Long id);
    Optional<Option> update(Long id, Option option);
    void addOptionToQuestion (Long optionId , Long questionId);
    void addOptionToSub (Long optionId, Long subid);
}   
