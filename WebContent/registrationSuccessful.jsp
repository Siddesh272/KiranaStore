<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Details</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@page import="java.util.*"%>
	<%@page import="com.Model.Customer"%>
	<%@page import="com.Dao.CustomerOperations"%>

	<%
		if (request.getParameter("Register").equalsIgnoreCase("Register")) {
			Customer c = new Customer();
			c.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			c.setCustomerName(request.getParameter("customerName"));
			c.setCustomerEmail(request.getParameter("email"));
			c.setCustomerPassword(request.getParameter("password"));
			c.setCustomerAddress(request.getParameter("address"));
			c.setCustomerContact(request.getParameter("contactNumber"));
			c.setActive(true);

			CustomerOperations co = new CustomerOperations();
			if (co.insertCustomerDetails(c) == 1) {
	%>
	<div class="acknowledgment-container">
        <h2 style="color: green;">Customer Registration Successful!</h2>
        <p><strong>Customer ID:</strong><%=c.getCustomerId() %></p>
        <p><strong>Customer Name:</strong><%=c.getCustomerName() %></p>
        <p><strong>Email:</strong><%=c.getCustomerEmail() %></p>
        <button id="login" onclick="redirectToLogin()">Log In</button>
    </div>
	<%
}else{
	out.print("Sorry! Please fill the form again.");
	response.sendRedirect("LoginPage.jsp");
}
}
%>

<script>
	function redirectToLogin()
	{
		window.location.href = "LoginPage.jsp"
	}
</script>
</body>
</html>