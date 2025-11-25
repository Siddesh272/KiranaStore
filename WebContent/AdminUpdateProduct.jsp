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
	<%@page import="java.util.*"%>
	<%@page import="com.Model.*"%>
	<%@page import="com.Dao.*"%>
	<%@page import="javax.servlet.http.*"%>

	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		HttpSession sess = request.getSession(false);
		Integer username = (Integer) session.getAttribute("username");

		if (username == null || session.getAttribute("username") == null) {
			response.sendRedirect("LoginPage.jsp");
			return;
		}

		AdminOperations ao = new AdminOperations();
		ProductOperations po = new ProductOperations();
		Admin ad = AdminOperations.getAdminDetailsById(username);

		ArrayList<Product> productList = ProductOperations.getAllProductDetails();

		String selectedProductId = request.getParameter("productID");
		Product selectedProduct = null;

		if (selectedProductId != null) {
			selectedProduct = ProductOperations.getProductDetailsById(Integer.parseInt(selectedProductId)); // Fetch product by ID
		}
	%>

	<nav class="navbar"> <a href="AdminHome.jsp">Home</a> <a
		href="AdminAddProduct.jsp">Add Product</a> <a
		href="AdminUpdateProduct.jsp">Update Product</a> <a
		href="AdminSearchCustomer.jsp">Search Customers</a> <a
		href="LogoutPage.jsp">Logout</a> </nav>

	<main>
	<h1>Update Products</h1>

	<section class="product-list"> <%
 	if (productList != null && !productList.isEmpty()) {
 %>
	<table>
		<tr>
			<th>Product ID</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Action</th>
		</tr>
		<%
			for (Product p : productList) {
		%>
		<tr>
			<td><%=p.getProductId()%></td>
			<td><%=p.getProductName()%></td>
			<td>Rs <%=p.getProductPrice()%></td>
			<td><%=p.getProductQuantity()%></td>
			<td>
				<form method="post" action="AdminUpdateProduct.jsp">
					<button class="update-product" type="submit" name="productID"
						value="<%=p.getProductId()%>">Update</button>
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} else {
	%>
	<h2>No Products Available</h2>
	<%
		}
	%> </section> <!-- Update Form for Selected Product --> <%
 	if (selectedProduct != null) {
 %>
	<div class="container">
		<h2>Update Product Details</h2>
		<table>
			<form action="updateProduct" method="post"
				enctype="multipart/form-data">
				<tr>
					<div>
						<td><label for="productId">Product ID:</label></td>
						<td><input type="text" id="productID" name="productID"
							value="<%=selectedProduct.getProductId()%>" readonly></td>
					</div>
				</tr>
				<tr>
					<div>
						<td><label for="productName">Product Name:</label></td>
						<td><input type="text" id="productName" name="productName"
							value="<%=selectedProduct.getProductName()%>" required></td>
					</div>
				</tr>
				<tr>
					<div>
						<td><label for="productPrice">Product Price:</label></td>
						<td><input type="text" id="productPrice" name="productPrice"
							value="<%=selectedProduct.getProductPrice()%>" required></td>
					</div>
				</tr>
				<tr>
					<div>
						<td><label for="productQuantity">Product Quantity:</label></td>
						<td><input type="text" id="productQuantity"
							name="productQuantity"
							value="<%=selectedProduct.getProductQuantity()%>" required></td>
					</div>
				</tr>
				<tr>
					<div>
						<td><label for="imagePath">Image Path:</label></td>
						<td><input type="file" id="imagePath" name="imagePath"
							value="<%=selectedProduct.getImagePath()%>"></td>
					</div>
				</tr>
				<tr>
					<div>
						<td><button type="submit" name="updateProduct" value="update">Update</button></td>
						<td><button type="submit" name="updateProduct" value="delete">Delete</button></td>
					</div>
				</tr>
			</form>
		</table>
	</div>
	<%
		}
	%> </main>

</body>
</html>