package com.example.final_module_3.repository;

import com.example.final_module_3.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAllProduct();

    Product findProductById(int id);

    void insertProduct(Product product);

    boolean deleteProductById(int id);

    boolean updateProduct(Product product);
}
