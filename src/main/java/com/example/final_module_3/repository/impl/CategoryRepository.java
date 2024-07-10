package com.example.final_module_3.repository.impl;

import com.example.final_module_3.connection.DBConnect;
import com.example.final_module_3.model.Category;
import com.example.final_module_3.repository.ICategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private Connection connection;
    private static final String FIND_ALL_CATEGORY = "SELECT * FROM category";

    public CategoryRepository() {
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    @Override
    public List<Category> findAllCategory() {
        List<Category> categoryList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category();
                category.setIdCategory(id);
                category.setCategoryName(name);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }
}
