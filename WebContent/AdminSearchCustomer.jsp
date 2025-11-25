<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Customer</title>
<link rel="stylesheet" href="css/searchCustomer.css">
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
Admin ad = AdminOperations.getAdminDetailsById(username);

ArrayList<Customer> cu = (ArrayList<Customer>) sess.getAttribute("customers");
%>


<nav class="navbar">
        <a href="AdminHome.jsp">Home</a>
        <a href="AdminAddProduct.jsp">Add Product</a>
        <a href="AdminSearchCustomer.jsp">Search Customers</a>
        <a href="LogoutPage.jsp">Logout</a>
        <form method="post" action="adminsearch">
        <input type="text" placeholder="Search by Name" id="searchByName" name="searchName">
        <input type="submit" id="hidden" hidden>
        </form>
    </nav>
    <div class="container">
        <h2>Customer List</h2>
        <table>
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                    <th>Customer Email</th>
                    <th>Customer Contact</th>
                    <th>Customer Address</th>
                </tr>
            </thead>
            <tbody>
            <%
            for(int i=0; i<cu.size(); i++){
            	Customer c = cu.get(i);
            %>
                <tr>
                    <td><%=c.getCustomerId() %></td>
                    <td><%=c.getCustomerName() %></td>
                    <td><%=c.getCustomerEmail() %></td>
                    <td><%=c.getCustomerContact() %></td>
                    <td><%=c.getCustomerAddress() %></td>
                </tr>
            <%} %>
            </tbody>
        </table>
    </div>
</body>
</html>