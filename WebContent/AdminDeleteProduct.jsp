<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Product</title>
<link rel="stylesheet" href="css/updateProduct.css">

</head>
<body>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
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
ProductOperations po = new ProductOperations();
Admin ad = ao.getAdminDetailsById(username);

Product p = (Product) sess.getAttribute("product");
%>



	<nav class="navbar">
		    <a href="AdminHome.jsp">Home</a>
	        <a href="AdminAddProduct.jsp">Add Product</a>
	        <a href="AdminUpdateProduct.jsp">Update Product</a>
	        <a href="AdminDeleteProduct.jsp">Delete Product</a>
	        <a href="AdminSearchCustomer.jsp">Search Customers</a>
	        <a href="LogoutPage.jsp">Logout</a>
	        
	        <div class="user-info">
				<span> <form method="post" action="adminSearchProduct"><input id="search" name="search" type="text"
					placeholder="Search. . . "><input type="submit" hidden id="hidden"></form>
			</div>
	</nav>
<% if(p!=null){%>	
	<div class="container">
	<table>
	<form action = "updateProduct" method = "post">
	<tr>
	    <div>
	    <td><label for = "productId">Product ID:</label></td>
	    <td><input type = "text" id = "productID" name = "productID" value=<%=p.getProductId() %> required></td>
	    </div>
	    </tr>
	    <tr>
	    <div>
	    <td><label for = "productName">Product Name:</label></td>
	    <td><input type ="text"id="productName"name="productName" value=<%=p.getProductName() %> required></td>
	    </div>
	    </tr>
	    <tr>
	    <div>
	    <td><label for = "productPrice">Product Price:</label></td>
	    <td><input type ="text"id="productPrice"name="productPrice" value=<%=p.getProductPrice() %> required></td>
	    </div>
	    </tr>
	    <tr>
	    <div>
	    <td><label for = "productQuantity">Product Quantity:</label></td>
	    <td><input type ="text"id="productQuantity"name="productQuantity" value=<%=p.getProductQuantity() %> required></td>
	    </div>
	    </tr>
	    <tr>
	    <div>
	    <td><label for="imagePath">Image Path:</label></td>
	    <td><input type="text" id="imagePath" name="imagePath" value=<%=p.getImagePath() %> required></td>
	    </div>
	    </tr>
	    <tr>
	    <div>
	    <td><button type="submit" name="updateProduct" value="update">Update Product</button></td>
	    <td><button tpe="submit" name="updateProduct" value="delete">Delete Product</button></td>
	    </div>
	    </tr>
	</form>
	</table>
	</div>
	<%}else{ %>
		<h2>Products Deleted Successfully !</h2>
<% } %>
	</body>
	</html>
