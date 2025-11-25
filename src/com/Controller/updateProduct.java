package com.Controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Dao.ProductOperations;
import com.Model.Product;

/**
 * Servlet implementation class updateProduct
 */
@WebServlet("/updateProduct")
@MultipartConfig
public class updateProduct extends HttpServlet {
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

		if(req.getParameter("updateProduct").equalsIgnoreCase("update")){
			Product p = new Product();
			p.setProductId(Integer.parseInt(req.getParameter("productID")));
			p.setProductName(req.getParameter("productName"));
			p.setProductPrice(Double.parseDouble(req.getParameter("productPrice")));
			p.setProductQuantity(Integer.parseInt(req.getParameter("productQuantity")));

			Part filePart = req.getPart("imagePath");
			if (filePart != null) {
				InputStream inputStream = filePart.getInputStream();
				byte[] imageBytes = new byte[(int) filePart.getSize()];

				inputStream.read(imageBytes);
				p.setImagePath(imageBytes);
			}

			p.setCustomerId(446320);
			p.setReserved(false);

			if(ProductOperations.updateProductDetails(p) == 1){
				resp.sendRedirect("AdminUpdateProduct.jsp");
				return;
			}

		}

		if(req.getParameter("updateProduct").equalsIgnoreCase("delete")){
			Product p = (Product) sess.getAttribute("product");
			int pid = Integer.parseInt(req.getParameter("productID"));
			if(ProductOperations.deleteProductDetailsById(pid) == 1){
				resp.sendRedirect("AdminHome.jsp");
				return;
			}else{
				resp.sendRedirect("AdminUpdateProduct.jsp");
				return;
			}
		}

	}

}
