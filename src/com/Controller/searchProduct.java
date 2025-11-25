package com.Controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.ProductOperations;
import com.Model.Product;

/**
 * Servlet implementation class searchProduct
 */
@WebServlet("/searchProduct")
public class searchProduct extends HttpServlet {
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

		ProductOperations po = new ProductOperations();
		String sp = req.getParameter("search").toLowerCase();
		ArrayList<Product> arr = ProductOperations.getAllProductDetails();
		if(sp.equals("")){
			sess.setAttribute("products", arr);
		}

		ArrayList<Product> searchP = new ArrayList<Product>();
		int l = arr.size();

		for(int i=0; i<l; i++){
			String name = arr.get(i).getProductName().toLowerCase();
			if(name.contains(sp)){
				searchP.add(arr.get(i));
			}
		}

		sess.setAttribute("products", searchP);
		resp.sendRedirect("HomePage.jsp");
		return;

	}

}
