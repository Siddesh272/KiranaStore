package com.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.CustomerOperations;
import com.Model.Customer;

/**
 * Servlet implementation class updateProfile
 */
@WebServlet("/updateProfile")
public class updateProfile extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);

		HttpSession sess = req.getSession(false);
		int username = (int) sess.getAttribute("username");

		if(sess.getAttribute("username")==null){
			resp.sendRedirect("LoginPage.jsp");
			return;
		}

	    CustomerOperations co = new CustomerOperations();
	    Customer c1 = CustomerOperations.getCustomerDetailsById(username);

	    if(req.getParameter("submit1").equalsIgnoreCase("save")){

			Customer c2 = new Customer();
			c2.setCustomerId(username);
			c2.setCustomerName(req.getParameter("cname1"));
			c2.setCustomerEmail(req.getParameter("cemail1"));
			c2.setCustomerPassword(c1.getCustomerPassword());
			c2.setCustomerAddress(req.getParameter("caddress1"));
			c2.setCustomerContact(req.getParameter("cphone1"));
			c2.setActive(true);

			if(CustomerOperations.updateCustomerDetails(c2) == 1) {
				resp.sendRedirect("MyProfile.jsp");
				return;
			}else{
				resp.sendRedirect("HomePage.jsp");
				return;
			}
	    }
	}

}
