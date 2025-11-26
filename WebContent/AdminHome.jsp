<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="css/adminHome.css">
</head>
<body>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
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
	Admin ad = ao.getAdminDetailsById(username);
%>

<nav class="navbar"> 
	<a href="AdminHome.jsp">Home</a> 
	<a href="AdminAddProduct.jsp">Add Product</a> 
	<a href="AdminUpdateProduct.jsp">Update Product</a> 
	<a href="AdminSearchCustomer.jsp">Search Customers</a> 
	<a href="LogoutPage.jsp">Logout</a> 
</nav>
<div class="container">
	<h2>Customer Transaction Information</h2>
	<table>
		<thead>
			<tr>
				<th>Customer ID</th>
				<th>Customer Name</th>
				<th>Customer Email</th>
				<th>Transaction ID</th>
				<th>Total Purchase</th>
			</tr>
		</thead>
		<tbody>
			<%
				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;

				try {
					// --- CRITICAL FIX: Corrected the database path ---
					Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
					con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
					// ---------------------------------------------------

					String sql = "select cu.cId, cu.cName, cu.cEmail, tr.tId, tr.tTotalAmount from Customer cu inner join Transactions tr on cu.cId=tr.tCId";

					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();

					while (rs.next()) {
			%>

			<tr>
				<td><%=rs.getInt(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getInt(4)%></td>
				<td>&#8377 <%=rs.getDouble(5)%></td>
			</tr>

			<%
				}

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					// Log the specific error message to the console
					System.out.println("Database Error in AdminHome.jsp: " + e.getMessage());
					// You might want to display an error row here if an error occurs
				} finally {
					// Ensure resources are closed
					if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
					if (pst != null) try { pst.close(); } catch (SQLException ignore) {}
					if (con != null) try { con.close(); } catch (SQLException ignore) {}
				}
			%>
		</tbody>
	</table>
</div>


</body>
</html>