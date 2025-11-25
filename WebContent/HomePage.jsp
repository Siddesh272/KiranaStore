<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.Model.*"%>
<%@page import="com.Dao.*"%>
<%@page import="javax.servlet.http.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" href="css/homepage.css">
</head>
<script>
	function showAddCartAlert(productId) {
		alert("Product " + productId + " added to cart");
	}
</script>
<body>
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
		CustomerOperations co = new CustomerOperations();
		Customer c = CustomerOperations.getCustomerDetailsById(username);
	%>
	<header>
	<div class="nav-container">
		<div class="logo">
			<a href="HomePage.jsp">Kirana<span>Store</span></a>
		</div>
		<nav>
		<ul>
			<li><a href="HomePage.jsp">Home</a></li>
			<li><a href="MyProfile.jsp">My Profile</a></li>
			<li><a href="CartPage.jsp">Cart</a></li>
			<li><a href="LogoutPage.jsp">Logout</a></li>
		</ul>
		</nav>
		<div class="user-info">
			<span id="userName"><%=c.getCustomerName()%></span>
		</div>
	</div>
	</header>

	<main>
	<h1>
		Hello, <span id="userName"><%=c.getCustomerName()%></span>, Welcome
		to Kirana Store
	</h1>

	<section class="product-list"> <%
 	ProductOperations po = new ProductOperations();
 	ArrayList<Product> p = (ArrayList<Product>) sess.getAttribute("products");
	
 	List<Product> prod = ProductOperations.getAllProductDetails();
 	
 	for (int i = 0; i < prod.size(); i++) {

 		
 		byte[] imageByte = prod.get(i).getImagePath();
 		String image = "";
 		if (imageByte != null) {
 			image = Base64.getEncoder().encodeToString(imageByte);
 		}
 %>
	<div class="card">
		<img src="data:image/jpeg;base64, <%=image%>" width=25%>
		<h2><%=p.get(i).getProductName()%></h2>
		<p class="price">
			&#8377 
			<%=p.get(i).getProductPrice()%></p>
		<form method="post" action="addToCart">
			<button class="add-to-cart" type="submit" name="cartButton"
				value=<%=p.get(i).getProductId()%>
				onclick="showAddCartAlert('<%=p.get(i).getProductId()%>')">Add
				to Cart</button>
		</form>
	</div>
	<%
		}
	%> </section> </main>

</body>
</html>