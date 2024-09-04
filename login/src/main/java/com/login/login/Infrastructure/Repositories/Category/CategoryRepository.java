package com.login.login.Infrastructure.Repositories.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.login.Domain.Categories;

public interface CategoryRepository  extends JpaRepository<Categories, Long>{

}
