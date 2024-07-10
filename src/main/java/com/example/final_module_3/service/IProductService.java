package com.example.final_module_3.service;

import com.example.final_module_3.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAllProduct();

    Product findProductById(int id);

    void insertProduct(Product product);

    boolean deleteProductById(int id);

    boolean updateProduct(Product product);
}
