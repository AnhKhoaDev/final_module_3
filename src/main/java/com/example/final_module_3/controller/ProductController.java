package com.example.final_module_3.controller;

import com.example.final_module_3.model.Category;
import com.example.final_module_3.model.Product;
import com.example.final_module_3.service.ICategoryService;
import com.example.final_module_3.service.IProductService;
import com.example.final_module_3.service.impl.CategoryService;
import com.example.final_module_3.service.impl.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IProductService productService;
    private ICategoryService categoryService;

    public ProductController() {
        this.productService = new ProductService();
        this.categoryService = new CategoryService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url  = request.getPathInfo();
        switch (url) {
            case "/list":
                try {
                    this.renderPageListProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case "/new":
                try {
                    this.renderPageNewProduct(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case "/edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    this.renderPageEditProduct(request,response, id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url  = request.getPathInfo();
        switch (url) {
            case "/add":
                try {
                    this.addNewProduct(request);
                    response.sendRedirect(request.getContextPath() + "/product/list");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/delete":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    this.deleteProduct(request,id);
                    response.sendRedirect(request.getContextPath() + "/product/list");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/update":
                try {
                    this.updateProduct(request);
                    response.sendRedirect(request.getContextPath() + "/product/list");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public void renderPageListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Product> products = this.productService.findAllProduct();
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/product/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Category> categories = this.categoryService.findAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/product/new.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageEditProduct(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, SQLException {
        Product product = this.productService.findProductById(id);
        request.setAttribute("product", product);
        List<Category> categories = this.categoryService.findAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/product/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    public void addNewProduct(HttpServletRequest request) throws ServletException, IOException, SQLException{
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        Product product = new Product();
        product.setProductName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setColor(color);
        product.setDescription(description);
        product.setCategory(new Category(categoryId));

        productService.insertProduct(product);
    }

    public void deleteProduct(HttpServletRequest request, int id) throws ServletException, IOException, SQLException {
        this.productService.deleteProductById(id);
    }

    public void updateProduct(HttpServletRequest request) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        Product product = new Product();
        product.setIdProduct(Integer.parseInt(request.getParameter("idProduct")));
        product.setProductName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setColor(color);
        product.setDescription(description);
        product.setCategory(new Category(categoryId));

        productService.updateProduct(product);
    }
}
