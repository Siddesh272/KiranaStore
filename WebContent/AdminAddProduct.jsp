<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Add Product</title>
<link rel="stylesheet" href="css/addProduct.css">
</head>
<body>
<%@page import="java.util.*" %>
<%@page import="com.Model.*" %>
<%@page import="com.Dao.*" %>
<%@page import="javax.servlet.http.*" %>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

HttpSession sess = request.getSession(false);
Integer username = (Integer)session.getAttribute("username");

if(username == null || session.getAttribute("username")==null){
	response.sendRedirect("LoginPage.jsp");
	return;
}

CustomerOperations co = new CustomerOperations();
AdminOperations ao = new AdminOperations();
Admin ad = ao.getAdminDetailsById(username);
%>

<nav class="navbar">
        <a href="AdminHome.jsp">Home</a>
        <a href="">Add Product</a>
        <a href="LogoutPage.jsp">Logout</a>
</nav>
<div class="container">
        <h2>Add Product</h2>
        <form id="addProductForm" method="post" action="addProduct" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productId">Product ID</label>
                <input type="number" id="productId" name="productId" placeholder="Enter Product ID">
            </div>
            <div class="form-group">
                <label for="productName">Product Name</label>
                <input type="text" id="productName" name="productName" placeholder="Enter Product Name">
            </div>
            <div class="form-group">
                <label for="productPrice">Product Price</label>
                <input type="number" id="productPrice" name="productPrice" placeholder="Enter Product Price" step="0.01">
            </div>
            <div class="form-group">
                <label for="productQuantity">Product Quantity</label>
                <input type="number" id="productQuantity" name="productQuantity" placeholder="Enter Product Quantity" min="1">
            </div>
            <div class="form-group">
                <label for="productImage">Product Image</label>
                <input type="file" id="productImage" name="productImage" placeholder="Enter Image Path">
            </div>
            <input type="submit" name="addproduct" value="Submit">
        </form>
    </div>
</body>
</html>