package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.AdminOperations;
import com.Dao.CustomerOperations;
import com.Dao.ProductOperations;
import com.Model.Admin;
import com.Model.Customer;
import com.Model.Product;

/**
 * Servlet implementation class adminLogin
 */
@WebServlet("/adminLogin")
public class adminLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);

		if(req.getParameter("adminlogin").equalsIgnoreCase("LogIn")){
			int id = Integer.parseInt(req.getParameter("adminId"));
			String pass = req.getParameter("adminPassword");

			AdminOperations ao = new AdminOperations();
			Admin ad = AdminOperations.getAdminDetailsById(id);

			CustomerOperations co = new CustomerOperations();
			ProductOperations po = new ProductOperations();
			ArrayList<Customer> c = CustomerOperations.getAllCustomerDetails();
			Product p = ProductOperations.getProductDetailsById(101);

			if(ad == null){
		            req.setAttribute("errorMessage", "Customer ID not matched, please enter valid credentials.");
		            req.getRequestDispatcher("AdminLogin.jsp").forward(req, resp);
			}else{
				if(ad.getAdminPassword().equals(pass)){

					HttpSession session = req.getSession();
					session.setAttribute("username", id);
					session.setAttribute("customers", c);
					session.setAttribute("product", p);

					resp.sendRedirect("AdminHome.jsp");
				}else{
					req.setAttribute("errorMessage", "Password not matched!");
	                req.getRequestDispatcher("AdminLogin.jsp").forward(req, resp);
				}
			}
		}

	}

}
