
package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.CustomerOperations;
import com.Dao.ProductOperations;
import com.Model.Cart;
import com.Model.Customer;
import com.Model.Product;

@WebServlet("/submitLogin")
public class submitLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    if (req.getParameter("customerLogin").equalsIgnoreCase("LogIn")) {
	        int id = Integer.parseInt(req.getParameter("customerId"));
	        String pass = req.getParameter("password");

	        CustomerOperations co = new CustomerOperations();
	        ProductOperations po = new ProductOperations();
	        Customer c = CustomerOperations.getCustomerDetailsById(id);
	        ArrayList<Cart> cart = new ArrayList<Cart>();
	        ArrayList<Product> products = ProductOperations.getAllProductDetails();

	        if (c == null) {
	            req.setAttribute("errorMessage", "Customer ID not matched, please enter valid credentials.");
	            req.getRequestDispatcher("LoginPage.jsp").forward(req, resp);
	        } else {
	            if (c.getCustomerPassword().equals(pass)) {
	                HttpSession session = req.getSession();
	                session.setAttribute("username", id);
	                session.setAttribute("cart", cart);
	                session.setAttribute("products", products);

	                resp.sendRedirect("HomePage.jsp");
	            } else {
	                req.setAttribute("errorMessage", "Password not matched!");
	                req.getRequestDispatcher("LoginPage.jsp").forward(req, resp);
	            }
	        }
	    }
	}

}
