package com.login.login.Infrastructure.Repositories.Option;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.login.Domain.Option_Question;

public interface OptionRepository  extends JpaRepository<Option_Question, Long>{

}
