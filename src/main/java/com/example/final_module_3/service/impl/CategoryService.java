package com.example.final_module_3.service.impl;

import com.example.final_module_3.model.Category;
import com.example.final_module_3.repository.ICategoryRepository;
import com.example.final_module_3.repository.impl.CategoryRepository;
import com.example.final_module_3.service.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    private static final ICategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllCategory();
    }
}
