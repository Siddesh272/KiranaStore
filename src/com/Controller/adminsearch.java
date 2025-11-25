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
import com.Model.Customer;

/**
 * Servlet implementation class adminsearch
 */
@WebServlet("/adminsearch")
public class adminsearch extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);

		HttpSession sess = req.getSession(false);
		Integer username = (Integer)sess.getAttribute("username");

		if(username == null || sess.getAttribute("username")==null){
			resp.sendRedirect("LoginPage.jsp");
			return;
		}

		String name = req.getParameter("searchName").toLowerCase();


		CustomerOperations co = new CustomerOperations();
		ArrayList<Customer> arr = CustomerOperations.getAllCustomerDetails();

		ArrayList<Customer> cu = new ArrayList<Customer>();

		if(name.equals("")){
			sess.setAttribute("customers", arr);
		}else{
			for(int i=0; i<arr.size(); i++){
				String cName = arr.get(i).getCustomerName().toLowerCase();
				if(cName.startsWith(name)){
					cu.add(arr.get(i));
				}
			}

			sess.setAttribute("customers", cu);
		}


		resp.sendRedirect("AdminSearchCustomer.jsp");
		return;

	}

}
