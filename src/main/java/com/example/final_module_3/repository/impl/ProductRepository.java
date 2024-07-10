package com.example.final_module_3.repository.impl;

import com.example.final_module_3.connection.DBConnect;
import com.example.final_module_3.model.Category;
import com.example.final_module_3.model.Product;
import com.example.final_module_3.repository.IProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    private Connection connection;

    public ProductRepository() {
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    private static final String FIND_ALL_PRODUCTS = "SELECT p.id_product, p.name_product,  p.price, p.quantity, p.color, p.description, c.name_category AS category FROM product p INNER JOIN category c ON p.id_category = c.id_category";
    private static final String FIND_PRODUCT_BY_ID = "SELECT p.id_product, p.name_product,  p.price, p.quantity, p.color, p.description, c.name_category AS CateogryName FROM product p INNER JOIN category c ON p.category_id = c.id_category WHERE p.id_category = ?";
    private static final String INSERT_PRODUCT = "INSERT INTO (name_product, price, quantity, color, description, id_category) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id_product = ?";
    private static final String UPDATE_PRODUCT = "UPDATE product set name_product=?, price=?, quantity=?, color=?, description=?, id_category=? WHERE id_product = ?";

    @Override
    public List<Product> findAllProduct() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_product");
                String name = resultSet.getString("name_product");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("category");

                Category category = new Category();
                category.setCategoryName(categoryName);

                Product product = new Product();
                product.setIdProduct(id);
                product.setProductName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setColor(color);
                product.setDescription(description);
                product.setCategory(category);

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product findProductById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name_product");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("category");

                Category category = new Category(categoryName);

                Product product = new Product();
                product.setIdProduct(id);
                product.setProductName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setColor(color);
                product.setDescription(description);
                product.setCategory(category);

                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void insertProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setInt(1, product.getIdProduct());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setString(7, product.getCategory().getCategoryName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProductById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getCategory().getCategoryName());
            preparedStatement.setInt(7, product.getIdProduct());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.findAllProduct();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
