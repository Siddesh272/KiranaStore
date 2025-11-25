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
import com.Model.Cart;
import com.Model.Product;

/**
 * Servlet implementation class removeFromCart
 */
@WebServlet("/removeFromCart")
public class removeFromCart extends HttpServlet {

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

		int pid = Integer.parseInt(req.getParameter("removefromcart"));
		ArrayList<Cart> arr = (ArrayList<Cart>)sess.getAttribute("cart");
		Product p = ProductOperations.getProductDetailsById(pid);

		int l = arr.size();
		for(int i=0; i<l; i++){
			if(arr.get(i).getPid() == pid){
				int quan = arr.get(i).getQuan();
				int prodQuan = p.getProductQuantity();
				p.setProductQuantity(prodQuan+quan);

				arr.remove(i);
				break;
			}
		}

		sess.setAttribute("cart", arr);

		resp.sendRedirect("CartPage.jsp");

	}

}
