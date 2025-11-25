package com.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Dao.ProductOperations;
import com.Model.Product;

/**
 * Servlet implementation class addProduct
 */
@WebServlet("/addProduct")
@MultipartConfig
public class addProduct extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getParameter("addproduct").equalsIgnoreCase("Submit")) {
			ProductOperations po = new ProductOperations();
			Product p = new Product();

			p.setProductId(Integer.parseInt(req.getParameter("productId")));
			p.setProductName(req.getParameter("productName"));
			p.setProductPrice(Double.parseDouble(req.getParameter("productPrice")));
			p.setProductQuantity(Integer.parseInt(req.getParameter("productQuantity")));
			p.setReserved(false);
			p.setCustomerId(1234567890);
			Part filePart = req.getPart("productImage");

			if (filePart != null) {
				InputStream inputStream = filePart.getInputStream();
				byte[] imageBytes = new byte[(int) filePart.getSize()];
				inputStream.read(imageBytes);
				p.setImagePath(imageBytes);
			}

			System.out.println("From addProdut - p.toString()" + p.toString());

			if (ProductOperations.insertProductDetails(p) == 1) {
				PrintWriter out = resp.getWriter();
				out.print("Product Inserted Successfully");
				resp.sendRedirect("AdminAddProduct.jsp");
			}
		}

	}

}
