
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Kirana Store</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login-container">
        <h2>Kirana<span>Store</span></h2>
        <form id="loginForm" name="loginForm" method="post" action="submitLogin">
            <div class="form-group">
                <label for="customerId">Customer ID</label>
                <input type="number" id="customerId" name="customerId" required />

            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" maxlength="30" required />
            </div>
            <input type="submit" name="customerLogin" value="LogIn" class="login-button">
            <div class="error-message" id="errorMessage"></div>
        </form>
        <p>New User? <a href="RegistrationPage.jsp">Register Yourself</a></p>
        <br>
        <p><a href="AdminLogin.jsp">Admin Login</a></p>
    </div>
    
    
    
    <% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <script>
        alert('<%= errorMessage %>');
    </script>
<%
    }
%>
    
    
    
</body>
</html>