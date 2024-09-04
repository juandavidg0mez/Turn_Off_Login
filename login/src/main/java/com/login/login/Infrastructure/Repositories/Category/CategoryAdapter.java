package com.login.login.Infrastructure.Repositories.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.login.Application.Services.ICategoryService;
import com.login.login.Domain.Categories;




@Service
public class CategoryAdapter implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Categories> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories save(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Categories> deteleById(Long id) {
        Optional<Categories> cOptional = categoryRepository.findById(id);
        if (cOptional.isPresent()) {
            categoryRepository.deleteById(id);
        }
        return cOptional;

    }

    @Override
    public Optional<Categories> findById(Long id) {
        Optional<Categories> cOptional = categoryRepository.findById(id);
        cOptional.ifPresent(db -> categoryRepository.findById(id));
        return cOptional;
    }

    @Override
    public Optional<Categories> update(Long id, Categories category) {
        Optional<Categories> cOptional = categoryRepository.findById(id);
        if (cOptional.isPresent()) {
            Categories existingCategory = cOptional.get();

            // campos de la categoría existente
            existingCategory.setName(category.getName());
            existingCategory.setAudit(category.getAudit());
        

            // categoría actualizada
            return Optional.of(categoryRepository.save(existingCategory));
        } else {
            return Optional.empty(); // Retorna Optional vacío si la categoría no se encuentra
        }

    }


}
