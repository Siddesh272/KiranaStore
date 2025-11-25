<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>

<link rel="stylesheet" href="css/adminLogin.css">
</head>
<body>
    <div class="container">
       
        <form id="adminLoginForm" name="loginForm" method="post" action="adminLogin">
             <h2>Admin Login</h2>
            <div class="form-group">
                <label for="adminId">Admin ID</label>
                <input type="number" id="adminId" name="adminId" placeholder="Enter Admin ID">
            </div>
            <div class="form-group">
                <label for="adminPassword">Password</label>
                <input type="password" id="adminPassword" name="adminPassword" placeholder="Enter Password">
            </div>
            <input type="submit" name="adminlogin" value="LogIn">
            <div class="error-message" id="errorMessage"></div>
        </form>
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