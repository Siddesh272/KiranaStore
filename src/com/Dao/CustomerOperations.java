package com.Dao;

import java.sql.*;
import java.util.ArrayList;


import com.Model.Customer;


public class CustomerOperations {
	public static int insertCustomerDetails(Customer c1) {
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "insert into Customer(cId, cName, cEmail, cPassword, cAddress, cContact, cIsActive) values(?, ?, ?, ?, ?, ?, ?)";

			pst = con.prepareStatement(sql);
			pst.setInt(1,  c1.getCustomerId());
			pst.setString(2,  c1.getCustomerName());
			pst.setString(3,  c1.getCustomerEmail());
			pst.setString(4,  c1.getCustomerPassword());
			pst.setString(5,  c1.getCustomerAddress());
			pst.setString(6,  c1.getCustomerContact());
			pst.setBoolean(7,  c1.isActive());

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	public static int deleteCustomerDetailsById(int id) {
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "delete from Customer where cId=?";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	public static int updateCustomerDetails(Customer c1) {

		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "update Customer set cName=?, cEmail=?, cContact=?, cAddress=? where cId=?";

			pst = con.prepareStatement(sql);
			pst.setString(1, c1.getCustomerName());
			pst.setString(2, c1.getCustomerEmail());
			pst.setString(3, c1.getCustomerContact());
			pst.setString(4, c1.getCustomerAddress());
			pst.setInt(5, c1.getCustomerId());

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print("Trying to update Customer ... : ");
		if(check == 1 ) {
			System.out.print("Customer Details Updated Successfully ... ");
		}else {
			System.out.println("Update Failed Unfortunately ... ");
		}
		return check;
	}

	public static ArrayList<Customer> getAllCustomerDetails(){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Customer> li = new ArrayList<Customer>();

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Customer";

			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();

			while(rs.next()) {
				Customer c1 = new Customer();
				c1.setCustomerId(rs.getInt(1));
				c1.setCustomerName(rs.getString(2));
				c1.setCustomerEmail(rs.getString(3));
				c1.setCustomerPassword(rs.getString(4));
				c1.setCustomerAddress(rs.getString(5));
				c1.setCustomerContact(rs.getString(6));
				c1.setActive(rs.getBoolean(7));

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

	public static Customer getCustomerDetailsByName(String name) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Customer c1 = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Customer where cName=?";

			pst = con.prepareStatement(sql);
			pst.setString(1, name);

			rs = pst.executeQuery();

			while(rs.next()) {
				c1 = new Customer();
				c1.setCustomerId(rs.getInt(1));
				c1.setCustomerName(rs.getString(2));
				c1.setCustomerEmail(rs.getString(3));
				c1.setCustomerPassword(rs.getString(4));
				c1.setCustomerAddress(rs.getString(5));
				c1.setCustomerContact(rs.getString(6));
				c1.setActive(rs.getBoolean(7));
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c1;
	}

	public static Customer getCustomerDetailsById(int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Customer c1 = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Customer where cId=?";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			while(rs.next()) {
				c1 = new Customer();
				c1.setCustomerId(rs.getInt(1));
				c1.setCustomerName(rs.getString(2));
				c1.setCustomerEmail(rs.getString(3));
				c1.setCustomerPassword(rs.getString(4));
				c1.setCustomerAddress(rs.getString(5));
				c1.setCustomerContact(rs.getString(6));
				c1.setActive(rs.getBoolean(7));
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c1;
	}

	public static ArrayList<Customer> searchCustomerByEmailDomain(String domain){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Customer> li = new ArrayList<Customer>();

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Customer where cName like ?";
			String s = "%@" + domain + ".com";

			pst = con.prepareStatement(sql);
			pst.setString(1, s);

			rs = pst.executeQuery();

			while(rs.next()) {
				Customer c1 = new Customer();
				c1.setCustomerId(rs.getInt(1));
				c1.setCustomerName(rs.getString(2));
				c1.setCustomerEmail(rs.getString(3));
				c1.setCustomerPassword(rs.getString(4));
				c1.setCustomerAddress(rs.getString(5));
				c1.setCustomerContact(rs.getString(6));
				c1.setActive(rs.getBoolean(7));

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