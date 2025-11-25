package com.Controller;

import java.io.IOException;
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
 * Servlet implementation class adminSearchProduct
 */
@WebServlet("/adminSearchProduct")
public class adminSearchProduct extends HttpServlet {

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
		int sp = Integer.parseInt(req.getParameter("search"));
		ArrayList<Product> arr = ProductOperations.getAllProductDetails();
		Product p = ProductOperations.getProductDetailsById(101);

		int l = arr.size();

		for(int i=0; i<l; i++){
			if(arr.get(i).getProductId() == sp){
				p = arr.get(i);
				break;
			}
		}

		sess.setAttribute("product", p);
		resp.sendRedirect("AdminUpdateProduct.jsp");
		return;
	}

}
