<%--
  Created by IntelliJ IDEA.
  User: khoadev
  Date: 9/7/24
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.final_module_3.model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.final_module_3.model.Category" %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new.css">

    <%--boostrap--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h2>Add New Product</h2>
    <form action="${pageContext.request.contextPath}/product/add" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" id="price" name="price" required>
            <span id="priceError" class="text-danger"></span>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" required>
            <span id="quantityError" class="text-danger"></span>
        </div>
        <div class="form-group">
            <label for="color">Color</label>
            <select id="color" name="color" required>
                <option value="">Select Color</option>
                <option value="Red">Red</option>
                <option value="Blue">Blue</option>
                <option value="Black">Black</option>
                <option value="White">White</option>
                <option value="Yellow">Yellow</option>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" rows="4" required></textarea>
        </div>
        <div class="form-group">
            <label for="category">Category</label>
            <select id="category" name="category" required>
                <option value="">Select Category</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-green">Create</button>
        <button type="button" class="btn btn-gray" onclick="location.href='/product/list'">Back</button>
    </form>
</div>
<script>
    function validateForm() {
        var isValid = true;

        var price = document.getElementById("price").value;
        if (price == "" || price <= 10000000) {
            document.getElementById("priceError").innerText = "Price must be greater than 10,000,000 VND";
            isValid = false;
        } else {
            document.getElementById("priceError").innerText = "";
        }

        var quantity = document.getElementById("quantity").value;
        if (quantity == "" || quantity <= 0) {
            document.getElementById("quantityError").innerText = "Quantity must be a positive integer";
            isValid = false;
        } else {
            document.getElementById("quantityError").innerText = "";
        }

        return isValid;
    }
</script>
</body>
</html>
