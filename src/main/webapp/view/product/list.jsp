<%--
  Created by IntelliJ IDEA.
  User: khoadev
  Date: 9/7/24
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.final_module_3.model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<% List<Product> products = (List<Product>) request.getAttribute("products"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">

    <%--boostrap--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h2>Management Product</h2>
    <div class="search-form">
        <form action="${pageContext.request.contextPath}/product/search" method="get">
            <label for="productName">Product Name</label>
            <input type="text" id="productName" name="productName" placeholder="Enter Product Name">

            <label for="price">Price</label>
            <input type="text" id="price" name="price" placeholder="Enter Price">

            <label for="category">Category</label>
            <input type="text" id="category" name="category" placeholder="Enter Category">

            <label for="color">Color</label>
            <input type="text" id="color" name="color" placeholder="Enter Color">

            <button type="submit">Search</button>
            <button type="reset">Clear</button>
        </form>
    </div>

    <button onclick="location.href='/product/new'">Add Product</button>

    <table>
        <thead>
        <tr>
            <th>STT</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${product.productName}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.category.categoryName}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/product/edit" method="get" class="mt-3">
                        <input type="hidden" name="id" value="${product.id}">
                        <button type="submit" class="btn btn-Info" style="width: 100px;">Edit</button>
                    </form>

                    <form action="${pageContext.request.contextPath}/product/delete" method="post" class="mt-3"
                          onsubmit="return confirm('Bạn có muốn xóa sản phẩm này không?');">
                        <input type="hidden" name="id" value="${product.idProduct}">
                        <button type="submit" class="btn btn-Info" style="width: 100px;">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
