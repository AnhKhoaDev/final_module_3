package com.example.final_module_3.service.impl;

import com.example.final_module_3.model.Product;
import com.example.final_module_3.repository.IProductRepository;
import com.example.final_module_3.repository.impl.ProductRepository;
import com.example.final_module_3.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    private static final IProductRepository productRepository = new ProductRepository();


    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }

    @Override
    public boolean deleteProductById(int id) {
        return productRepository.deleteProductById(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }
}
