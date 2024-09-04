package com.login.login.Infrastructure.Repositories.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.login.Domain.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>{

}
