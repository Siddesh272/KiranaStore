package com.Dao;

import java.sql.*;
import java.util.ArrayList;

import com.Model.Customer;
import com.Model.Product;


public class ProductOperations {
	public static int insertProductDetails(Product p1) {
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "insert into Product(pId, pName, pPrice, pQuantity, pIsReserved, pImage) values(?, ?, ?, ?, ?, ?)";

			pst = con.prepareStatement(sql);
			pst.setInt(1,  p1.getProductId());
			pst.setString(2,  p1.getProductName());
			pst.setDouble(3,  p1.getProductPrice());
			pst.setInt(4,  p1.getProductQuantity());
			pst.setBoolean(5,  p1.isReserved());

			pst.setBytes(6, p1.getImagePath());

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	public static int deleteProductDetailsById(int id) {
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "delete from Product where pId=?";

			pst = con.prepareStatement(sql);
			pst.setInt(1,  id);

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	public static int updateProductDetails(Product p1) {

		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "update Product set pName=?, pPrice=?, pQuantity=?, pImage=? where pId=?";

			pst = con.prepareStatement(sql);
			pst.setString(1,  p1.getProductName());
			pst.setDouble(2, p1.getProductPrice());
			pst.setInt(3, p1.getProductQuantity());

			byte[] newImage = p1.getImagePath();
			if(newImage.length == 0)
			{
				 byte[] oldImage = getProductDetailsById(p1.getProductId()).getImagePath();
				 pst.setBytes(4, oldImage);
			} else {
				pst.setBytes(4, newImage);
			}
			pst.setInt(5, p1.getProductId());

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	public static ArrayList<Product> getAllProductDetails(){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Product> li = new ArrayList<Product>();

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Product";

			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();

			while(rs.next()) {
				Product p1 = new Product();
				p1.setProductId(rs.getInt(1));
				p1.setProductName(rs.getString(2));
				p1.setProductPrice(rs.getDouble(3));
				p1.setProductQuantity(rs.getInt(4));
				p1.setReserved(rs.getBoolean(5));
				p1.setCustomerId(rs.getInt(6));
				p1.setImagePath(rs.getBytes(7));

				li.add(p1);
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return li;
	}

	public static Product getProductDetailsById(int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Product p1 = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Product where pId=?";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			while(rs.next()) {
				p1 = new Product();
				p1.setProductId(rs.getInt(1));
				p1.setProductName(rs.getString(2));
				p1.setProductPrice(rs.getDouble(3));
				p1.setProductQuantity(rs.getInt(4));
				p1.setReserved(rs.getBoolean(5));
				p1.setCustomerId(rs.getInt(6));
				p1.setImagePath(rs.getBytes(7));			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p1;
	}

	public static Product getProductDetailsByName(String name) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Product p1 = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Product where pName=?";

			pst = con.prepareStatement(sql);
			pst.setString(1, name);

			rs = pst.executeQuery();

			while(rs.next()) {
				p1 = new Product();
				p1.setProductId(rs.getInt(1));
				p1.setProductName(rs.getString(2));
				p1.setProductPrice(rs.getDouble(3));
				p1.setProductQuantity(rs.getInt(4));
				p1.setReserved(rs.getBoolean(5));
				p1.setCustomerId(rs.getInt(6));
				p1.setImagePath(rs.getBytes(7));			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p1;
	}

	public static ArrayList<Customer> getCustomersWhoHavePlacedOrder(){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Customer> li = new ArrayList<Customer>();

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select c.cId, c.cName, c.cEmail, c.cAddress, c.cContact, c.cIsActive from Customer c inner join Product p on c.cId=p.pCId order by c.cId";

			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();

			while(rs.next()) {
				Customer c1 = new Customer();
				c1.setCustomerId(rs.getInt(1));
				c1.setCustomerName(rs.getString(2));
				c1.setCustomerEmail(rs.getString(3));
				c1.setCustomerAddress(rs.getString(4));
				c1.setCustomerContact(rs.getString(5));
				c1.setActive(rs.getBoolean(6));

				li.add(c1);
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return li;
	}

	public static ArrayList<Product> getProductsWhosePriceIsGreaterThan(int limit){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Product> li = new ArrayList<Product>();

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Product where pPrice>?";

			pst = con.prepareStatement(sql);
			pst.setInt(1,  limit);

			rs = pst.executeQuery();

			while(rs.next()) {
				Product p1 = new Product();
				p1.setProductId(rs.getInt(1));
				p1.setProductName(rs.getString(2));
				p1.setProductPrice(rs.getDouble(3));
				p1.setProductQuantity(rs.getInt(4));
				p1.setReserved(rs.getBoolean(5));
				p1.setCustomerId(rs.getInt(6));
				p1.setImagePath(rs.getBytes(7));
				li.add(p1);
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return li;
	}

	public static ArrayList<Customer> getCustomersWithOrdersMoreThan(int limit){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Customer> li = new ArrayList<Customer>();

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select c.cId, c.cName, c.cEmail, c.cAddress, c.cContact, c.cIsActive from Customer c inner join Product p on c.cId=p.pCId group by c.cId having count(p.pId)>limit";

			pst = con.prepareStatement(sql);
			pst.setInt(1,  limit);

			rs = pst.executeQuery();

			while(rs.next()) {
				Customer c1 = new Customer();
				c1.setCustomerId(rs.getInt(1));
				c1.setCustomerName(rs.getString(2));
				c1.setCustomerEmail(rs.getString(3));
				c1.setCustomerAddress(rs.getString(4));
				c1.setCustomerContact(rs.getString(5));
				c1.setActive(rs.getBoolean(6));

				li.add(c1);
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return li;
	}
}