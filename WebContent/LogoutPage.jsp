<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successfully LoggedOut</title>
<link rel="stylesheet" href="css/logout.css">
</head>
<body>
<%@page import="javax.servlet.http.*" %>
<%
HttpSession sess = request.getSession(false);
session.invalidate();
%>

<div class="logout-container">
        <h2>Log Out Successful!</h2>
        <p>You have successfully logged out. You will be redirected to the login page shortly.</p>
        <a href="LoginPage.jsp">Return to Login Page</a>
</div>
<script>
window.onload = function() {
	setInterval(function(){
		window.location.href="LoginPage.jsp";
	}, 3000);
}
</script>
</body>
</html>