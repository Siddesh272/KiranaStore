<%@page import="java.io.Console"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" href="css/cart.css">
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

		ArrayList<Cart> arr = (ArrayList<Cart>) sess.getAttribute("cart");

		CustomerOperations co = new CustomerOperations();
		ProductOperations po = new ProductOperations();
		Customer c = co.getCustomerDetailsById(username);
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
	<div class="cart-container">
		<h2>My Cart</h2>

		<%
			double totalCost = 0;
			int no_of_items = 0;
			ArrayList<Integer> pid = new ArrayList<Integer>();

			System.out.println("Arr:" + (arr));
			for (int i = 0; i < arr.size(); i++) {
				Cart ca = arr.get(i);
				Product p = po.getProductDetailsById(ca.getPid());

				totalCost += ca.getCost();
				no_of_items += ca.getQuan();
				pid.add(ca.getPid());
		%>

		<div class="cart-item">
			<div class="item-info">
				<h3><%=p.getProductName()%></h3>
				<p>
					&#8377 <%=ca.getCost()%></p>
			</div>
			<div class="item-controls">
				<form method="post" action="cartMinusOne">
					<button type="submit" name="minusOne" value=<%=ca.getPid()%>
						class="opbuttons">-</button>
				</form>
				<input type="text" id="itemQuantity" name="itemQuantity"
					value=<%=ca.getQuan()%> min="1">
				<form method="post" action="cartPlusOne">
					<button type="submit" name="plusOne" value=<%=ca.getPid()%>
						class="opbuttons">+</button>
				</form>
				<form method="post" action="removeFromCart">
					<button type="submit" name="removefromcart" value=<%=ca.getPid()%>
						class="opbuttons">Remove</button>
				</form>
			</div>
		</div>
		<%
			}
		%>

		<div class="cart-item">
			<div class="item-info">
				<h3>Total Cost</h3>
				<p>
					&#8377 <%=totalCost%></p>
			</div>
			<div class="item-control">
				<button type="submit" name="checkout" onclick="invoice()">Proceed
					to Buy</button>
			</div>
		</div>
	
	</main>
	<div class="container" id="invoice-container">
		<h2>Invoice</h2>
		<table>
			<thead>
				<tr>
					<th>Transaction ID</th>
					<th>Customer ID</th>
					<th>Products ID</th>
					<th>Number of Items</th>
					<th>Total Amount</th>
				</tr>
			</thead>
			<tbody>
				<%
					Random random = new Random();
					int tid = random.nextInt(1000000 - 100000) + 100000;

					int intProductIds = 0;
					for (int id : pid) {
						intProductIds = intProductIds * 10 + id;
					}

					Transactions t = new Transactions();
					t.setTransactionId(tid);
					t.setNoOfItems(no_of_items);
					t.setTotalAmount(totalCost);
					t.setCustomerId(username);
					t.setProductId(intProductIds);

					TransactionsOperations to = new TransactionsOperations();
					boolean proceedWithUpdate = to.checkForProductIdForeignKey(t.getProductId()); // Check if product exists
				%>
				<tr>
					<td><%=tid%></td>
					<td><%=t.getCustomerId()%></td>
					<td><%=t.getProductId()%></td>
					<td><%=t.getNoOfItems()%></td>
					<td>&#8377 <%=totalCost%></td>
				</tr>
				<%
					if (!proceedWithUpdate) {
						if (to.updateTransactionDetails(t) == 1) { // If update succeeds
				%>

				<%
					}
					} else {
						if (to.insertTransactionDetails(t) == 1) { // If insert succeeds
				%>
				<tr>
					<td><%=t.getTransactionId()%></td>
					<td><%=t.getCustomerId()%></td>
					<td><%=t.getProductId()%></td>
					<td><%=t.getNoOfItems()%></td>
					<td>&#8377 <%=t.getTotalAmount()%></td>
				</tr>
				<%
					}
					}
				%>

			</tbody>
		</table>
	</div>

	<script>
		function invoice() {
			if (window.confirm("Proceed to Buy?")) {
				document.getElementById("invoice-container").style.setProperty(
						"display", "block");
			}
		}
	</script>
</body>
</html>