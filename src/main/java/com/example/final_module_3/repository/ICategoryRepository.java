package com.example.final_module_3.repository;

import com.example.final_module_3.model.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAllCategory();
}
