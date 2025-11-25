<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
<link rel="stylesheet" href="css/myprofile.css">
</head>
<body>

	<%@page import="java.util.*"%>
	<%@page import="com.Model.Customer"%>
	<%@page import="com.Dao.CustomerOperations"%>
	<%@page import="javax.servlet.http.*"%>

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
    Customer c1 = co.getCustomerDetailsById(username);
    
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
	</div>
	</header>

	<main>
	<div class="profile-container">
		<h2>My Profile</h2>
		<form id="profileForm" method="post" action="updateProfile">
			<div class="form-group">
				<label for="cname1">Name:</label> <input type="text" id="name"
					name="cname1" value="<%=c1.getCustomerName() %>"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="cemail1">Email:</label> <input type="text" id="email"
					name="cemail1" value="<%=c1.getCustomerEmail() %>"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="cphone1">Phone:</label> <input type="text" id="phone"
					name="cphone1" value="<%=c1.getCustomerContact() %>"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="caddress1">Address:</label>
				<textarea id="address" name="caddress1" readonly="readonly"><%=c1.getCustomerAddress() %></textarea>
			</div>

			<%if(c1.isActive()){%>

			<div class="button-container">
				<button type="button" onclick="updateProfile()">Edit</button>
				<button type="submit" id="save" name="submit1" value="save" hidden>Save</button>
			</div>

			<%
			}else{	
			%>
			<div class="button-container">
				<button type="submit" id="reactive" name="submit1" value="reactive"
					onclick="enterPassword()">ReActive</button>
			</div>
			<%} %>

			<input type="text" name="confirmPassword" id="confirmPassword" hidden>
		</form>
	</div>
	</main>

	<script>
        function updateProfile() {
            document.getElementById('name').removeAttribute('readonly');
            document.getElementById('email').removeAttribute('readonly');
            document.getElementById('phone').removeAttribute('readonly');
            document.getElementById('address').removeAttribute('readonly');
            
            document.getElementById('save').removeAttribute('hidden');
        }
        
        function enterPassword() {
        	document.getElementById("confirmPassword").value = window.prompt("Confirm Password: ", "");
        }
    </script>
</body>
</html>